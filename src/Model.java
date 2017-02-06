import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Model extends Observable implements Iterable<Address> {
    private List<Address> addresses = new ArrayList<>();

    public Model(){}

    public Model(String filename) throws IOException {
        try(BufferedReader input = new BufferedReader(new FileReader(new File(filename)))){
            input.lines().forEach(line -> addresses.add(Address.parse(line)));
        }
    }

    public void add(Address address) {
        addresses.add(address);
        setChanged();
        notifyObservers();
    }
    @Override
    public Iterator<Address> iterator() {
        return addresses.iterator();
    }
}
