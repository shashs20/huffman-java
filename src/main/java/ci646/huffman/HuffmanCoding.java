package ci646.huffman;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HuffmanCoding implements Serializable {

    private final Map<Character, List<Boolean>> code;
    private final List<Boolean> data;

    public HuffmanCoding(Map<Character, List<Boolean>> code, List<Boolean> data) {
        this.code = code;
        this.data = data;
    }

    public Map<Character, List<Boolean>> getCode() {
        return code;
    }

    public List<Boolean> getData() {
        return data;
    }

    public void save(String path) {
        try {
            FileOutputStream fout = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Optional<HuffmanCoding> read(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            return Optional.of((HuffmanCoding) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}