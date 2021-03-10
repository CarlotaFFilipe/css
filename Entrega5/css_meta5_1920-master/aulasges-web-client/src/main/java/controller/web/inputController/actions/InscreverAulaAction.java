package controller.web.inputController.actions;
import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IInscreverAulaServiceRemote;
import presentation.web.model.InscreveUtenteNaAulaModel;

@Stateless
public class InscreverAulaAction  extends Action{

	@EJB private IInscreverAulaServiceRemote iiasr;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InscreveUtenteNaAulaModel model = createHelper(request);
		request.setAttribute("model", model);

		
		if (validInput(model)) {
			iiasr.inscreverAula();
			iiasr.inscricao(model.getMod(),model.getTipo());
			iiasr.indicaAula(model.getAula(), model.getNumUtente());
			model.clearFields();
			model.addMessage("Inscrito com sucesso!");
		}else{
			model.addMessage("Error validating incricao data");
			request.getRequestDispatcher("/addInscricao/newInscricao.jsp").forward(request, response);
		}
	}


	private boolean validInput(InscreveUtenteNaAulaModel model) {

		// check if discount type is filled and a valid number
		boolean result = isFilled(model, model.getMod(), "Modalidade must be filled") 
				&& isInt(model, model.getMod(), "Modalidade type with invalid characters");

		// check if discount type is filled and a valid number
		result &= isFilled(model, model.getAula(), "Aula must be filled") 
				&& isInt(model, model.getAula(), "Aula type with invalid characters");

		// check if discount type is filled and a valid number
		result &= isFilled(model, model.getTipo(), "Tipo de inscricao must be filled") 
				&& isTipoDeInscricao(model, model.getTipo(), "Tipo de inscricao type with invalid characters");

		// check if discount type is filled and a valid number
		result &= isFilled(model, model.getNumUtente(), "Numero de Utente must be filled") 
				&& isInt(model, model.getNumUtente(), "Numero de Utente type with invalid characters");

		return result;
	}


	private InscreveUtenteNaAulaModel createHelper(HttpServletRequest request) {
		// Create the object model
		InscreveUtenteNaAulaModel model = new InscreveUtenteNaAulaModel();
		model.setInscreverAulaHandler(iiasr);

		// fill it with data from the request
		model.setMod(request.getParameter("modalidade"));
		model.setTipo(request.getParameter("tipo"));
		model.setAula(request.getParameter("aula"));
		model.setNumUtente(request.getParameter("numUtente"));

		return model;
	}

}
