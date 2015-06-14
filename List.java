/*
 * Implemnta uma lista dinamica
 * metodos: -add(java.lang.Object) adiciona um elemento ao fim da lista 
 * 			-remove(int where) remove um elemento na posição where 
 * 			-get(int index) retorna o elemento da posição index
 * 			-last() retorna o último elemento da lista
 * 			-size() retorna o tamanho da lista
 * 			-empty(): boolean retorna se a lista está vazia
 * 			-removeAll() remove todos os elementos
 * 			-toString() retorna uma string com todos os elementos da lista
 *todos os metodos de remoção e adição retornam a própria classe para permitir method chaining
 *
 */
public class List {

	
	private java.lang.Object[] list;
	private int size;
	
	public List(){
		size = 0;
		list = new java.lang.Object[0];
	}
	
	public List remove(int where){
		if(where >= size){throw new IllegalArgumentException();}
		java.lang.Object aux[] = new java.lang.Object[--size];
		for(int i = 0; i < where;i++){
			aux[i] = list[i];
		}
		
		for(int i = where + 1;i<size+1;i++){
			aux[i-1] = list[i];
		}
		
		list = aux;
		return this;
	}
	public List removeAll(){
		Object[] aux = new Object[0];
		list = aux;
		this.size = 0;
		return this;
	}
	
	public List add(java.lang.Object el){
		java.lang.Object aux[] = new  java.lang.Object[list.length + 1];
		for(int i = 0; i < size;i++){
			aux[i] = list[i];
		}
		aux[size++] = el;
		list = aux;
		return this;
	}
	/*
	 * como get retorna um object é necessário dar casting: Classe obj = (Classe)lista.get(0); 
	 * */
	public  java.lang.Object get(int index){
		if(index >= this.size || this.empty()){
			throw new IllegalArgumentException();
		}
		return this.list[index];
	}
	public int size(){
		return this.size;
	}
	boolean empty(){
		return size == 0;
	}
	public  java.lang.Object last(){
		return list[size-1];
	}
	public String toString(){
		if(this.empty()){return "[]";}
		String s = "[";
		for(int i = 0; i < size-1;i++){
			s = s + this.list[i].toString();
			s = s + ",";
		}
		s+=list[size-1];
		s+="]";
		return s;
	}
	
	public static void main(String[] args){
		List lista = new List();
		lista.add("adriano").add("Rafael");
		lista.removeAll();
		lista.add("adrinao");
		System.out.println(lista.toString());
	}
	
}
