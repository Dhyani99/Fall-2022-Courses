import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class HashTable {
    int counter = 0;
    int collisions;
    int size;
    String[] hashTable;
    boolean sizeExceeded = false;

    public HashTable(int size) {
        collisions = 0;
        this.size = size;
        hashTable = new String[size];

    }

    public int computeHash(String s) {

        int hashValue = 0;
        for (int i = 0; i < s.length(); i++) {
            hashValue += s.charAt(i);
        }
        return hashValue % size;
    }

    public int computeNewSize(int size) {
        size++;
        for (int i = 2; i <= size / 2; i++) {
            if (size % i == 0) {
                size++;
                i = 2;
            } else {
                continue;
            }
        }

        return size;
    }

    public void quadraticProbing(String s, int hash) {
        int i = 1;

        while (true) {
            int quadraticHash = (hash + 2 * i - 1) % size;
            if (hashTable[quadraticHash] == null) {
                hashTable[quadraticHash] = s;
                counter++;
                if (counter > (size / 2)) {
                    sizeExceeded = true;
                }
                return;
            }
            i++;
            collisions++;
        }

    }

    public boolean addWordToHashTable(String s) {

        int hash = computeHash(s);
        if (hashTable[hash] == null) {
            hashTable[hash] = s;
            counter++;
            if (counter > (size / 2)) {
                return false;
            }
        } else {
            collisions++;
            quadraticProbing(s, hash);
            if (sizeExceeded) {
                return false;
            }
        }

        return true;
    }

    public void printHashTable() {
        System.out.println("Number of collisions: " + collisions);
        System.out.println("HashTable size: " + size);
    }

    public ArrayList<String> addWordsToList() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("words.txt")));
        String word;
        while ((word = br.readLine()) != null) {
            list.add(word);
        }
        return list;
    }

    public static void main(String args[]) throws Exception {
        int size = 31;
        HashTable hashTable = new HashTable(size);
        ArrayList<String> list = hashTable.addWordsToList();
        boolean inserted = true;
        for (int i = 0; i < list.size(); i++) {
            inserted = hashTable.addWordToHashTable(list.get(i));
            if (!inserted) {
                size = hashTable.computeNewSize(size * 2);
                hashTable = new HashTable(size);
                list = hashTable.addWordsToList();
                i = -1;
            }
        }
        System.out.println("Total number of words: " + list.size());
        hashTable.printHashTable();
    }
}
