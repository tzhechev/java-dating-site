package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.UserDAO;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;

/**
 * Servlet implementation class Images
 */
public class Images extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 2500619515965683225L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Images() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private InputStream pictureStream=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String username = request.getParameter("user");
		if (username != null && !username.equals("")) {
			synchronized (this) {
				Facade.doInTransaction(new TransactionAction(){
		
					@Override
					public void execute() {
						User user = UserDAO.getUserByName(username);
						if (user !=null) {
							Blob blob = UserDAO.getUserPictureBlob(user);
							if (blob != null){
								try {
									Images.this.pictureStream = blob.getBinaryStream();
								} catch (SQLException e) {
									e.printStackTrace();
								}					
							}
						}
		
					}
				});
				OutputStream outputStream = response.getOutputStream();
			
				if (pictureStream != null){
					byte buffer [] = new byte[4];
					pictureStream.read(buffer);
					outputStream.write(buffer);
					response.setContentType(checkContentType(buffer));
					buffer = new byte[10000];
					int flag = 0;
					while (flag!=-1){
						flag = pictureStream.read(buffer);
						outputStream.write(buffer);
					}
					outputStream.flush();			
				}
				pictureStream.close();
				outputStream.close();	
		}
		

		}


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private String checkContentType(byte [] buff){
		if (Arrays.equals(buff, new byte[]{(byte)0x47, (byte)0x49, (byte)0x46, (byte)0x38})) {
			return "image/gif";
		}
		else if (Arrays.equals(Arrays.copyOf(buff, 2), new byte[]{(byte)0x42, (byte)0x4d})) {
			return "image/x-ms-bmp";
		}
		else if (Arrays.equals(buff, new byte[]{(byte)0xff, (byte)0xd8, (byte)0xff, (byte)0xe0})) {
			return "image/jpeg";
		}
		else if (Arrays.equals(buff, new byte[]{(byte)0x89, (byte)0x50, (byte)0x4e, (byte)0x47})) {
			return "image/x-png";
		}
		return "image/plain";
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
