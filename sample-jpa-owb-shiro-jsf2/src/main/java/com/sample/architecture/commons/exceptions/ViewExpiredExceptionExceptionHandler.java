package com.sample.architecture.commons.exceptions;

import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJBException;
import javax.el.ELException;
import javax.enterprise.context.NonexistentConversationException;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cjrequena
 * 
 */
public class ViewExpiredExceptionExceptionHandler extends ExceptionHandlerWrapper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewExpiredExceptionExceptionHandler.class);

	private ExceptionHandler wrapped;

	public ViewExpiredExceptionExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable t = context.getException();

			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
			NavigationHandler nav = fc.getApplication().getNavigationHandler();

			if (t instanceof ELException) {
				Throwable cause = t.getCause();
				if (cause instanceof EJBException && cause.getCause() instanceof PersistenceException) {
					try {

						// Push some useful stuff to the request scope for use in the page
						requestMap.put("currentException", t);

						// If the view roow it null, then set it to the view that expired.
						if (fc.getViewRoot() == null) {
							UIViewRoot view = fc.getApplication().getViewHandler().createView(fc, "/index.jsp");
							fc.setViewRoot(view);
						}

						// Navigate to the viewExpired outcome. Use viewExpired.xhtml for implicit
						// handling or
						// configure a navigation rule in the faces-config.xml for this outcome
						nav.handleNavigation(fc, null, "/error.xhtml");

						// Skip to rendering the response
						fc.renderResponse();

					} catch (Exception e) {
						LOGGER.error("ViewExpiredExceptionExceptionHandler.handle() error {}", e);
					} finally {
						// Remove this unhandled exception
						i.remove();
					}
				}
			} else if (t instanceof NonexistentConversationException) {

				try {

					// Push some useful stuff to the request scope for use in the page
					requestMap.put("currentException", t);

					// If the view roow it null, then set it to the view that expired.
					if (fc.getViewRoot() == null) {
						UIViewRoot view = fc.getApplication().getViewHandler().createView(fc, "/index.jsp");
						fc.setViewRoot(view);
					}

					// Navigate to the viewExpired outcome. Use viewExpired.xhtml for implicit
					// handling or
					// configure a navigation rule in the faces-config.xml for this outcome
					nav.handleNavigation(fc, null, "/viewExpired.xhtml");

					// Skip to rendering the response
					fc.renderResponse();

				} catch (Exception e) {
					LOGGER.error("ViewExpiredExceptionExceptionHandler.handle() error {}", e);
				} finally {
					// Remove this unhandled exception
					i.remove();
				}

			} else if (t instanceof ViewExpiredException) {
				ViewExpiredException vee = (ViewExpiredException) t;

				try {

					// Push some useful stuff to the request scope for use in the page
					requestMap.put("currentViewId", vee.getViewId());

					// If the view roow it null, then set it to the view that expired.
					if (fc.getViewRoot() == null) {
						UIViewRoot view = fc.getApplication().getViewHandler().createView(fc, vee.getViewId());
						fc.setViewRoot(view);
					}

					// Navigate to the viewExpired outcome. Use viewExpired.xhtml for implicit
					// handling or
					// configure a navigation rule in the faces-config.xml for this outcome
					nav.handleNavigation(fc, null, "/viewExpired.xhtml");

					// Skip to rendering the response
					fc.renderResponse();

				} catch (Exception e) {
					LOGGER.error("ViewExpiredExceptionExceptionHandler.handle() error {}", e);
				} finally {
					// Remove this unhandled exception
					i.remove();
				}
			} else if (t instanceof FacesException) {
				FacesException fe = (FacesException) t;

				try {

					// Push some useful stuff to the request scope for use in the page
					StringBuffer message = new StringBuffer();

					if (fe.getCause() != null) {
						message.append(" Cause: " + fe.getCause());
					}

					if (fe.getMessage() != null) {
						message.append(" Detail: " + fe.getMessage());
					}
					requestMap.put("cause", message.toString());

					// Navigate to the viewExpired outcome. Use viewExpired.xhtml for implicit
					// handling or
					// configure a navigation rule in the faces-config.xml for this outcome
					nav.handleNavigation(fc, null, "/error.xhtml");

					// Skip to rendering the response
					fc.renderResponse();

				} catch (Exception e) {
					LOGGER.error("ViewExpiredExceptionExceptionHandler.handle() error {}", e);
				} finally {
					// Remove this unhandled exception
					i.remove();
				}
			}
		}
		// At this point, the queue will not contain any ViewExpiredEvents.
		// Therefore, let the parent handle them.
		getWrapped().handle();
	}
}
