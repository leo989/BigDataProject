package resources;

import java.util.LinkedList;

public class Dataset {
	private static LinkedList<Punto> punti;
	private static Punto puntoUtente = new Punto(42.460559, 12.386838);
		
	public static LinkedList<Punto> getPunti(){
		if(punti != null)
			return punti;
		else{
			return generaPunti();
		}
	}
	
	private static LinkedList<Punto> generaPunti(){
		punti = new LinkedList<Punto>();
		//roma
		Punto p = new Punto(41.884899,12.478988);
		p.addProdotto(new Prodotto("gamberi",10,1));
		p.addProdotto(new Prodotto("soiola",20, 0.5));
		punti.add(p);
		//civitavecchia
		p = new Punto(42.094911,11.79517);
		p.addProdotto(new Prodotto("gamberi",15,1));
		p.addProdotto(new Prodotto("soiola",15,2));
		p.addProdotto(new Prodotto("cozze",100,0.1));
		punti.add(p);
		//napoli
		p = new Punto(40.857448,14.268097);
		p.addProdotto(new Prodotto("gamberi",15,1));
		p.addProdotto(new Prodotto("soiola",15,2));
		p.addProdotto(new Prodotto("cozze",180,0.2));
		punti.add(p);
		//orbetello
		p = new Punto(42.441131,11.21265);
		p.addProdotto(new Prodotto("gamberi",30,1.5));
		p.addProdotto(new Prodotto("soiola",10,3));
		p.addProdotto(new Prodotto("vongole",200,1));
		punti.add(p);
		//montalto di castro
		p = new Punto(42.354537, 11.606610);
		p.addProdotto(new Prodotto("gamberi",30,1.5));
		p.addProdotto(new Prodotto("soiola",10,3));
		p.addProdotto(new Prodotto("vongole",200,1));
		punti.add(p);
		//tarquinia
		p = new Punto(42.260106, 11.757795);
		p.addProdotto(new Prodotto("gamberi",30,1.5));
		p.addProdotto(new Prodotto("vongole",200,1.2));
		punti.add(p);
		//monteromano
		p = new Punto(42.258073, 11.896840);
		p.addProdotto(new Prodotto("trote",10,2));
		p.addProdotto(new Prodotto("vongole",200,1.2));
		punti.add(p);
		//ladispoli
		p = new Punto(41.957163, 12.076302);
		p.addProdotto(new Prodotto("gamberi",100,0.8));
		p.addProdotto(new Prodotto("vongole",10,3));
		p.addProdotto(new Prodotto("spigole",15,5));
		punti.add(p);
		//anzio
		p = new Punto(41.461290, 12.621904);
		p.addProdotto(new Prodotto("gamberi",100,0.8));
		p.addProdotto(new Prodotto("spigole",15,5));
		punti.add(p);
		//terracina
		p = new Punto(41.310915, 13.232832);
		p.addProdotto(new Prodotto("spigole",15,5));
		p.addProdotto(new Prodotto("soiola",200,3));
		punti.add(p);
		//montefiascone
		p = new Punto(42.556173, 12.035764);
		p.addProdotto(new Prodotto("trote",200,0.8));
		p.addProdotto(new Prodotto("lattarino",400,1));
		punti.add(p);
		//viterbo
		p = new Punto(42.430835, 12.107520);
		p.addProdotto(new Prodotto("gamberi",50,2));
		p.addProdotto(new Prodotto("lattarino",100,2));
		punti.add(p);
		//bracciano
		p = new Punto(42.101653, 12.174316);
		p.addProdotto(new Prodotto("trote",100,1.5));
		p.addProdotto(new Prodotto("soiola",200,2.5));
		punti.add(p);
		//vetralla
		p = new Punto(42.316908, 12.058074);
		p.addProdotto(new Prodotto("gamberi",80,3));
		p.addProdotto(new Prodotto("cozze",100,3));
		punti.add(p);
		//soriano nel cimino
		p = new Punto(42.427779, 12.236783);
		p.addProdotto(new Prodotto("soiola",50,2));
		p.addProdotto(new Prodotto("vongole",70,5));
		punti.add(p);
		//marta
		p = new Punto(42.535287, 11.925151);
		p.addProdotto(new Prodotto("trote",100,3));
		p.addProdotto(new Prodotto("lattarino",300,0.5));
		punti.add(p);
		//anguillara
		p = new Punto(42.090850, 12.271623);
		p.addProdotto(new Prodotto("trote",200,4));
		p.addProdotto(new Prodotto("lattarino",150,2));
		punti.add(p);
		
		
		
		return punti;
	}
	
	public static LinkedList<Punto> getPuntiByProdotto(String prodotto){
		LinkedList<Punto> ris = new LinkedList<Punto>();
		for(Punto p: getPunti()){
			for(Prodotto prod: p.getProdotti()){
				if(prod.getProdotto().equals(prodotto))
					ris.add(p);
			}
		}
		return ris;
	}

	public static Punto getPuntoUtente() {
		return puntoUtente;
	}

	public static void setPuntoUtente(Punto puntoUtente) {
		Dataset.puntoUtente = puntoUtente;
	}
}
