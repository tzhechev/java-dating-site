package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.session.Facade;
import db.session.TransactionAction;

public class BaseTransactionalServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	@Override
	public final void service(final ServletRequest arg0, final ServletResponse arg1)
			throws ServletException, IOException {
		Facade.doInTransaction(new TransactionAction() {

			@Override
			public void execute() {
				try {
					BaseTransactionalServlet.super.service(arg0, arg1);
				} catch (ServletException e) {
					throw new RuntimeException(e);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
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
