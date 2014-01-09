package gloskampen.model;
import java.io.*;
import java.util.*;
/**
 *
 * This class is responsible for reading and saving data to different "dictionaries".
 * To achieve this the class also keeps track of the valid languages by reading a static file.
 * @author André Karlsson
 * @version 0.2 2014-01-08
 * 
 */
public class FileHandling {
    private String mFilePath;
    private String mFailed = "Something went wrong!";
   
    private ArrayList<String> mDictionary = new ArrayList<>();
    private ArrayList<String> mValidLanguages = new ArrayList<>();
    
    private final String mFilePathLanguages = "src/gloskampen/files/languages/validlanguages.txt";
    private final String mFilePathDictionaries = "src/gloskampen/files/dictionaries/";
    
    /**
     * Call the constructor to fully initialize the class.
     */
    public void FileHandling(){
        this.readValidLanguages();
    }
    
    /**
     * This method simply passes on the inputed language for processing and validation.
     * @param mInLanguage the language of the dictionary you want to access. 
     * @return tempMessage a simple text that indicates if the language is valid or not.
     */ 
    public String AccessLanguage(String mInLanguage){
        String tempMessage = this.validateLanguage(mInLanguage);
        return tempMessage;
    }
    
    /**
     * This method will add a new language to the valid languages and create a new dictionary based on the inputed language name.
     * @param mInLanguage the new language to be added.
     * @return tempMessage a message that indicates if successful or not.
     */
    private String addNewLanguage(String mInLanguage){
        String mTempValidLanguage = mInLanguage;
        try{
            mValidLanguages.add(mTempValidLanguage);
            
            FileWriter mWriteFile = new FileWriter(mFilePathLanguages);
            
            for(int i = 0; i < mValidLanguages.size(); i++){
                mWriteFile.write(mValidLanguages.get(i));
                mWriteFile.write(System.getProperty("line.separator"));
            }
            mWriteFile.close();
            
            String tempMessage = "Successfully added the new language!";
            return tempMessage;

        }catch(IOException e){
            String tempMessage = "Error:" + e.getMessage();
            return tempMessage;
        }
    }
    
    /**
     * This method checks if the chosen language is valid and calls for @addNewLanguage if it not exists.
     * @param mTempLanguage the language you want to access and validate.
     * @return tempMessage a message that indicates if the process was successful or not.
     * @return mFailed a generic message that indicates that something went wrong.
     */
    private String validateLanguage(String mTempLanguage){
        for(int i = 0; i < mValidLanguages.size(); i++){
            if(mValidLanguages.contains(mTempLanguage)){
                mFilePath = mTempLanguage;
                this.readDictionary();
                String tempMessage = "It is a valid language!";
                return tempMessage;
            }else{
                this.addNewLanguage(mTempLanguage);
                mFilePath = mTempLanguage;
                File file = new File(mFilePathDictionaries + mTempLanguage+".txt");
                try{
                    file.createNewFile();
                }catch(IOException e){
                    System.out.println("Error:" + e.getMessage());
                }
                String tempMessage = "It is not a valid language! Creating one for you!";
                return tempMessage;
            }
        } return mFailed;
    }   
    
    /**
     * This method reads the locally storage file of valid languages that have existing dictionaries.
     * @return an ArrayList of valid languages.
     */
    private ArrayList readValidLanguages(){
        try{
            BufferedReader mReadFile = new BufferedReader(new FileReader(mFilePathLanguages)); 
            int mRow=0;
            while(true){
                String mText = mReadFile.readLine();
                if(mText == null){
                    break;
                }else{
                mValidLanguages.add(mText);
                mRow++;
                }
            }
            mReadFile.close();
        }catch(IOException e){
            System.out.println("Error:" + e.getMessage());
        }
        return mValidLanguages;
    }
  
    /**
     * Call this method to read the chosen dictionary and get an ArrayList with the result in return.
     * @return An ArrayList with the size of the dictionary and each index containing word:translation.
     */
    private ArrayList readDictionary(){
        try{
            BufferedReader mReadFile = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePathDictionaries + mFilePath + ".txt"), "UTF-8"));
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
     * @return tempMessage a message that indicates if the process was successful or not.
     */
    public String writeDictionary(ArrayList<String> tempDictionary){
        try{
            FileWriter mWriteFile = new FileWriter (mFilePathDictionaries + mFilePath + ".txt");
            BufferedWriter mOutStream = new BufferedWriter(mWriteFile);
            
            for(int i = 0; i < tempDictionary.size(); i++){
                mOutStream.write(tempDictionary.get(i));
                mOutStream.write(System.getProperty("line.separator"));
            }
            mOutStream.close();
            String tempMessage = "Data saved";
            return tempMessage;
            
        }catch(IOException e){
            String tempMessage = "Error:" + e.getMessage();
            return tempMessage;
        }
    }
    
    /**
     * @return an ArrayList of valid languages.
     */
    public ArrayList<String> getValidLanguages() {
        return mValidLanguages;
    }
    
    /**
     * @return an ArrayList of the chosen language.
     */
    public ArrayList<String> getDictionary() {
        return mDictionary;
    }

}