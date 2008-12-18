package servlets;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

import db.dao.UserDAO;
import db.entities.Picture;
import db.entities.User;
import db.session.Facade;
import db.session.HibernateSessionManager;
import db.session.TransactionAction;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("uploadStatus");
		final User user = (User) request.getSession().getAttribute("onlineUser");
		
		synchronized (this) {
			MultipartParser multiParse = null;
			try {
				multiParse = new MultipartParser(request, 1024*1024, false, true);
			}catch(IOException e) {
//				response.sendError(901, "Size overflow! Only files under 1MB allowed!");
				request.getSession().setAttribute("uploadStatus", "File is too large! Only images under 1MB accepted!");
				redirect(request, response, "pages/upload.jsp");
				return;
			}
			
			Part part = multiParse.readNextPart();
			FilePart fpart = null;
//			String username = null;
			final ByteBuffer tmpByte = ByteBuffer.allocate(1024*1024);
			boolean acceptedFileFormat = true;
			while (part != null) {
				if (part.isFile()){
					fpart = (FilePart)part;
					byte [] buff = new byte[4];
					int flag = fpart.getInputStream().read(buff);
					acceptedFileFormat = verifyContentType(buff);
					if (acceptedFileFormat){
						tmpByte.put(buff);
						buff = new byte[10000];
						while (flag!=-1){
							flag = fpart.getInputStream().read(buff);
							tmpByte.put(buff);
						}						
					}

				}
//				else {
//					ParamPart ppart = (ParamPart)part;
//					if (ppart.getName().equals("user")) {
//						username = ppart.getStringValue();
//					}
//				}
				part = multiParse.readNextPart();
			}
			
			if (fpart.getName().equals("file") && acceptedFileFormat){
//				final String uname = username;
				Facade.doInTransaction(new TransactionAction(){

					@Override
					public void execute() {
//						User usr = UserDAO.getUserByName(uname);
						if (user != null) {
							Session hbSession = HibernateSessionManager.getCurrentSession();
							Picture pic = UserDAO.getUserPicture(user);
							Blob blob = Hibernate.createBlob(tmpByte.array());
				
							if (pic != null) {
								pic.setUserId(user.getUserId());
								pic.setPicture(blob);
								hbSession.update(pic);							
							}
							else {
								pic = new Picture();
								pic.setUserId(user.getUserId());
								pic.setPicture(blob);
								hbSession.save(pic);
							}							
						}
					}
					
				});
				request.getSession().setAttribute("uploadStatus", "Success!");
				redirect(request, response, "pages/upload.jsp");
			}
			else {
//				response.sendError(902, "Wrong file type!\nAvailable file types are: gif, jpg, png, bmp");
				request.getSession().setAttribute("uploadStatus", "Wrong file type! \nAvailable file types are: gif, jpg, png, bmp");
				redirect(request, response, "pages/upload.jsp");
			}
			
		}
		
	}
	private boolean verifyContentType(byte [] buff){
		if (Arrays.equals(buff, new byte[]{(byte)0x47, (byte)0x49, (byte)0x46, (byte)0x38})) {
			return true;
		}
		else if (Arrays.equals(Arrays.copyOf(buff, 2), new byte[]{(byte)0x42, (byte)0x4d})) {
			return true;
		}
		else if (Arrays.equals(buff, new byte[]{(byte)0xff, (byte)0xd8, (byte)0xff, (byte)0xe0})) {
			return true;
		}
		else if (Arrays.equals(buff, new byte[]{(byte)0x89, (byte)0x50, (byte)0x4e, (byte)0x47})) {
			return true;
		}
		return false;
	}
	protected void redirect(HttpServletRequest request,
			HttpServletResponse response, String location) {
		try {
			response.sendRedirect(location);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
