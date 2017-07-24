package ic.ufal.br.location;

/**
 * Created by alunoic on 24/07/17.
 */
public class SimpleLocation {

    public String nome;

    private double latitude;
    private double longitude;

    public SimpleLocation() {
    }

    public SimpleLocation(String nome) {
        super();
        this.nome = nome;
    }

    public SimpleLocation(double latitude, double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public SimpleLocation(String nome, double latitude, double longitude) {
        super();
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distance(SimpleLocation other) {

        return (other.latitude - other.longitude);
    }

    public double distance(double latitude, double longitude) {

        return(latitude - longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {

        if (latitude > -14) {
            this.latitude = latitude;
        } else {
            System.out.println("Não é possível atribuir!");
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
