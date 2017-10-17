package a029187_miguelsilva.jogodaforca;

/**
 * Created by migue on 17-Oct-17.
 */

class Forca {

    private String[] palavras = {"item", "gratuito", "proibido", "rubrica", "recorde", "pudico", "menu", "ali", "raiz", "higiene", "somente", "sozinho", "coco", "flor", "cor", "ideia", "plateia", "europeia", "alcateia", "geleia", "apoia", "boia", "jiboia", "paranoia", "heroico", "joia", "deem", "leem", "veem", "creem", "voo", "enjoo", "abençoo", "perdoo", "feiura", "para", "pera", "pelo", "polo"};

    /*Forca() {
    }*/

    String randomWord() {
        int index = (int) (Math.random() * palavras.length);

        return palavras[index];
    }

}