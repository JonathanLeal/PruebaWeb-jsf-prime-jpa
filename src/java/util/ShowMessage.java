package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ShowMessage {
    
    // Método para mostrar mensajes de información
    public static void showInfo(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Método para mostrar mensajes de advertencia
    public static void showWarning(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Método para mostrar mensajes de error
    public static void showError(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Método para mostrar mensajes fatales
    public static void showFatal(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
