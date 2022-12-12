import java.io.*;
import java.util.ArrayList;

public class HashTable {

    int N;
    int SIZE;
    String[] HASH_TABLE;
    int COLLISION;

    public HashTable(int initialSize) {
        N = 0;
        SIZE = initialSize;
        HASH_TABLE = new String[initialSize];
    }

    public static ArrayList<String> readFile(String fileName){
        ArrayList<String> words = new ArrayList<>(20);
        File file = new File(fileName);
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null){
            words.add(st);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    public int hash(String key){

        int hashVal = 0;
        for (int i = 0; i < key.length(); i++)
            hashVal += key.charAt(i);
        return hashVal % SIZE;

    }

    public boolean loadFactorExceeded(){
        return ((float) N / SIZE) >= 0.5;
    }


    private void reInsert(String s) {
        String[] temp = HASH_TABLE;
        SIZE = SIZE * 2;
        SIZE = findNextPrime(SIZE);
        HASH_TABLE = new String[SIZE];

        for (String value : temp) {
            if (value != null) {
                addToHashTable(value);
            }
        }

        addToHashTable(s);
    }

    private int findNextPrime(int size) {
        while(true){
            boolean isPrime = true;
            for (int i = 2; i <= size/2; i++) {

                if(size % i == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) {
                return size;
            }
            size++;
        }
    }

    private void addToHashTable(String s) {

        int hash = hash(s);

        if(HASH_TABLE[hash] == null) {
            HASH_TABLE[hash] = s;
        }
        else {
            COLLISION++;
            int newHash = hash;
            int i = 1;
            while(true){
                newHash = (newHash + 2*i - 1) % SIZE;
                if(HASH_TABLE[newHash] == null){
                    HASH_TABLE[newHash] = s;
                    return;
                }
                COLLISION++;
                i++;
            }
        }
    }

    public void putHashTable(ArrayList<String> words){

        for (String word : words) {
            if (!loadFactorExceeded()) {
                addToHashTable(word);
            } else {
                reInsert(word);
            }
            N++;
        }

    }


    public static void main(String[] args){

        String fileName = "words.txt";
        ArrayList<String> words = readFile(fileName);
        HashTable table = new HashTable(31);
        table.putHashTable(words);

        System.out.println("Total Number of Collision: " + table.COLLISION);
        System.out.println("Total Number of Words: " + table.N);
        System.out.println("Size of Hash Table: " + table.SIZE);

    }
}
