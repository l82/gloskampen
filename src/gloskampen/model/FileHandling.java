package gloskampen.model;
import java.io.*;
import java.util.*;
/**
 *
 * This class is responsible for reading and saving data to different "dictionaries".
 * @author André
 * @version 0.1 2014-01-05
 * 
 */
public class FileHandling {
    
    private String mLanguage;
    private String mFilePath;
    private String mEncoding;
    private ArrayList<String> mDictionary = new ArrayList<>();
    
    /**
     * @param mInLanguage the language of the dictionary you want to access. 
     */ 
    public void FileHandling(String mInLanguage){
        this.switchLanguages(mInLanguage);
    }
    
    /**
     * Here we store all of our languages in different cases and this also helps us to check that the chosen language is valid language.
     * If you want to add a new language, simply create a new case with the name of the language and start with a capital letter.
     */
    private void switchLanguages(String mTempLanguage){
        switch(mTempLanguage){
            case "English":
                mFilePath = "English.txt";
                mLanguage = mTempLanguage;
                mEncoding = "UTF-8";
                break;
            case "German":
                mFilePath = "German.txt";
                mLanguage = mTempLanguage;
                mEncoding = "UTF-8";
                break;
            case "French":
                mFilePath = "French.txt";
                mLanguage = mTempLanguage;
                mEncoding = "UTF-8";
                break;                   
            default:
                System.out.println("Not a valid language.");
                break;
        }
    }
    
    /**
     * Call this method to read the chosen dictionary and get an ArrayList with the result in return.
     * @return An ArrayList with the size of the dictionary and each index containing word:translation.
     */
    public ArrayList readFile(){
        try{
            String tempFilePath = "src/gloskampentestcenter/dictionaries/" + mFilePath;
            BufferedReader mReadFile = new BufferedReader(new InputStreamReader(new FileInputStream(tempFilePath), mEncoding));
 
            int mRow=0;
            while(true){
                String mText = mReadFile.readLine();
                if(mText == null){
                    break;
                }
                if(mText.contains(",")){
                    mText = mText.replace(",", "");
                }
                mDictionary.add(mText);
                mRow++;
            }
            mReadFile.close();
        }catch(IOException e){
            System.out.println("Error:" + e.getMessage());
        }
        return mDictionary;
    }
    
    /**
     * Call this method to write the chosen dictionary. Will add separators if missing.
     * @param tempDictionary the ArrayList of the dictionary chosen before. 
     */
    public void writeFile(ArrayList<String> tempDictionary){
        try{
            String tempFilePath = "src/gloskampentestcenter/dictionaries/" + mFilePath;
            FileWriter  mWriteFile = new FileWriter (tempFilePath);
            BufferedWriter mOutStream = new BufferedWriter(mWriteFile);
            
            for(int i = 0; i < tempDictionary.size(); i++){
                mOutStream.write(tempDictionary.get(i));
                mOutStream.write(System.getProperty("line.separator"));
            }
            mOutStream.close();
            System.out.println("Data saved");
            
        }catch(IOException e){
            System.out.println("Error:" + e.getMessage());
        }
    }
}