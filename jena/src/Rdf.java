import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;


public class Rdf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		   Model model = ModelFactory.createDefaultModel();
		   String NS = 
				  
	       // create the resource
	       Resource johnSmith = model.createResource(personURI);

	      // add the property
	      johnSmith.addProperty(VCARD.FN, fullName);
		   
	}

}
