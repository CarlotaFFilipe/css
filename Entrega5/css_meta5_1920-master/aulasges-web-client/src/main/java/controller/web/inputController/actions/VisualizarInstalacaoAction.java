package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IVisualizarServiceRemote;
import presentation.web.model.VisualizarOcupacaoAulaModel;


@Stateless
public class VisualizarInstalacaoAction  extends Action{

	@EJB private IVisualizarServiceRemote visualizarHandler;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		VisualizarOcupacaoAulaModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {

			visualizarHandler.verInstalacao(model.getNome(),model.getDate());
			model.clearFields();
			model.addMessage("Customer successfully added.");

		} else
			model.addMessage("Error validating customer data");

		request.getRequestDispatcher("/vizualizarInstalacao/newVisualizar.jsp").forward(request, response);
	}




	private boolean validInput(VisualizarOcupacaoAulaModel model) {

		// check if designation is filled
		boolean result = isFilled(model, model.getNome(), "Nome must be filled.");

		// check if discount type is filled and a valid number
		result &= isFilled(model, model.getDate().toLocaleString(), "Date must be filled.");

		return result;
	}
	
	
	private VisualizarOcupacaoAulaModel createHelper(HttpServletRequest request) {
		// Create the object model
		VisualizarOcupacaoAulaModel model = new VisualizarOcupacaoAulaModel();
		model.setVisualizarHandler(visualizarHandler);

		// fill it with data from the request
		model.setNome(request.getParameter("nome"));
		model.setDate(request.getParameter("datas"));
		
		return model;
	}

}
