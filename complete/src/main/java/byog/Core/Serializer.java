package byog.Core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/* Class by David Robinson and Fady Nakhla */

public class Serializer {
    Save t;

    public void serialize(Save s) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Save.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(s);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            return;
        }
    }

    public Save unserialize() {
        try {
            FileInputStream fileIn = new FileInputStream("Save.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            t = (Save) in.readObject();
            in.close();
            fileIn.close();
            return t;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            return null;
        }
    }
}

