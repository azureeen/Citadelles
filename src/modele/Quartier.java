package modele;

public class Quartier {

    // Variables
    private String nom = "";
    private String type = "";
    private Integer coutConstruction = 0;
    private String caracteristiques = "";

    private boolean embelli;

    public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};

    /**
     * Constructeur Quartier
     * @param args : Object... args dans un constructeur est une utilisation de la fonctionnalité de Java "varargs". Cela permet de passer un nombre quelconque d'arguments (y compris aucun) au constructeur. Les arguments sont traités comme un tableau de Object, ce qui signifie que vous pouvez passer des arguments de différents types, mais vous devez gérer leur typage et leur ordre manuellement au sein du constructeur pour éviter les erreurs de casting ou de logique
     */
    public Quartier(Object... args) {
        setNom(args.length > 0 && args[0] != null ? (String) args[0] : null);
        setType(args.length > 1 && args[1] != null ? (String) args[1] : null);
        setCoutConstruction(args.length > 2 && args[2] != null ? (Integer) args[2] : null);
        setCaracteristiques(args.length > 3 && args[3] != null ? (String) args[3] : null);
    }

    /**
     * Getter var : nom
     * @return nom
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Setter var : nom
     * @param nom /
     */
    public void setNom(String nom) {
        this.nom = nom != null ? nom : "";
    }


    /**
     * Getter var : type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter var : type
     * @param type /
     */
    public void setType(String type) {
        if (type != null) {
            for (String t : TYPE_QUARTIERS) {
                if (type.equals(t)) {
                    this.type = t;
                    return;
                }
            }
        }
        this.type = "";
    }

    /**
     * Accesseur var : coutConstruction
     * @return coutConstruction
     */
    public Integer getCoutConstruction() {
        return coutConstruction;
    }

    /**
     * Setter var : coutConstruction
     * @param coutConstruction /
     */
    public void setCoutConstruction(Integer coutConstruction) {
        if (coutConstruction != null && coutConstruction >= 1 && coutConstruction <= 6) {
            this.coutConstruction = coutConstruction;
        } else {
            this.coutConstruction = 0;
        }
    }

    /**
     * Getter var : caracteristiques
     * @return caracteristiques
     */
    public String getCaracteristiques() {
        return caracteristiques ;
    }

    /**
     * Setter var : caracteristiques
     * @param caracteristiques /
     */
    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques != null ? caracteristiques : "";
    }

    /**
     * TODO : comment get
     * @return
     */
    public boolean getEmbelli() {
        return embelli;
    }

    /**
     * TODO : comment set
     * @param embelli
     */
    public void setEmbelli(boolean embelli) {
        this.embelli = embelli;
    }


    /**
     * TODO : comment method
     */
    public void embellir(){
        if(!this.getEmbelli()){
            this.coutConstruction+=1;
            this.setEmbelli(true);
            System.out.println("Le quartier à bien été embelli");
        }else{
            System.out.println("Quartier déjà embelli");
        }
    }
}
