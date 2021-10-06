import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        Airport airport = Airport.getInstance();
        final int hours = 2;
        Date dateNow = new Date();
        Date dateAddHours = new Date(dateNow.getTime() + hours * 3600 * 1000);

        List<Flight> flights = new ArrayList<>();
        for (Terminal terminal: airport.getTerminals()
             ) {
            for(Flight flight: terminal.getFlights()){
                flights.add(flight);
            }
        }

        List<Flight> aircraft = flights.stream()
                .filter(f -> f.getType() == Flight.Type.DEPARTURE)
                .filter(f -> f.getDate().after(dateNow))
                .filter(f -> f.getDate().before(dateAddHours))
                .sorted((f1, f2) -> {
                    if(f1.getDate() == f2.getDate()){
                        return 0;
                    }else if(f1.getDate().after(f2.getDate())){
                        return 1;
                    }else{
                        return -1;
                    }}).collect(Collectors.toList());

        for(Flight plane: aircraft){
            System.out.printf("борт:  %7s  вылет %tH:%tM\n",
                    plane.getCode(), plane.getDate(), plane.getDate());
        }
    }
}
