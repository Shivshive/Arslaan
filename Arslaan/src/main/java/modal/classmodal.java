package modal;

import java.util.ArrayList;
import java.util.List;

public class classmodal {

	String name;
	List<methodmodal> methods;
	
	public classmodal(){
		methods = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<methodmodal> getMethods() {
		return methods;
	}
	public void setMethods(List<methodmodal> methods) {
		this.methods = methods;
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Class Name "+getName()+"\n");
		sb.append("--------------------------------------\n");		
		for(methodmodal method : methods){
			sb.append("\n");
			sb.append(" Test Name "+method.getName()+" : "+method.getStatus());			
		}
		sb.append("--------------------------------------\n");		
		
		
		return sb.toString();
		
	}
	
}
