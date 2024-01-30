
package gestionefile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// la classe User implemente l'interfaccia Serializable
public class User implements Serializable {
    
    // campo necessario per la serializzazione
    public static final long serialVersionUID = 0;
    
    int id;
    String nome;
    String cognome;
    String ruolo;

    public User(int id, String nome, String cognome, String ruolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    
    // funzione che scrive l'oggetto in un flusso di dati passato come parametro
    private void writeObject(ObjectOutputStream out) throws IOException {
	out.defaultWriteObject();
  }

 // funzione che legge l'oggetto in un flusso di dati passato come parametro
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	in.defaultReadObject();
  }
    
}
