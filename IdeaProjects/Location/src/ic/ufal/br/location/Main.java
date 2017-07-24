package ic.ufal.br.location;

/**
 * Created by alunoic on 24/07/17.
 */
public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        SimpleLocation ic = new SimpleLocation(2, 3);

        SimpleLocation ufal = new SimpleLocation();
        ufal.setLatitude(-15);

        System.out.println("Latitude: " + ic.getLatitude() + " Longitude: " + ic.getLongitude());

        System.out.println("Distance: " +  ufal.distance(ic));

        // Java S2

    }
}
