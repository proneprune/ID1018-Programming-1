// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
                     // IOException
import java.util.*;  // LinkedList

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        LinkedList<String> synonymLines = new LinkedList<>();
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			synonymLines.add(synonymLine);
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[synonymLines.size()];
		synonymLines.toArray(synonymData);

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word)
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word)
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData,
	    String word)
	{
		// add code here
        String[] synData = new String[synonymData.length-1]; //ny string med 1 mindre index

        for (int i = 0; i < synData.length; i++)
        {
            if (synonymData[i].compareToIgnoreCase(getSynonymLine(synonymData, word)) != 0){
                synData[i] = synonymData[i];// lägger till synonymline i syndata om lines inte är samma
            }
            else{
                synData[i] = synonymData[i+1]; //lägger till nästa synonym line ifall de är samma
            }


        }
        return synData;
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym)
	{

        // add code here
        int index = synonymLineIndex(synonymData, word); //hittar index på ordet
        String[] synData = new String[synonymData.length];
        synData = synonymData;

        for(int i = 0; i < synonymData.length; i++)
        {
            synData[i] = synonymData[i];
            if(i == index) //när i blir index av ordet läggs synonymen till
                synData[index] += ", " + synonym;
        }
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
	public static void removeSynonym (String[] synonymData,
	    String word, String synonym)
	{
        // add code here
        int index = synonymLineIndex(synonymData, word);

        String synonymline = ""; //tom string för synonymer

        synonymline = synonymData[index];//fyll synoynmline med alla gamla synonymer
        int synonymlength = synonym.length();//hitta längden av hela stringen

        String nysynonymline = getSynonymLine(synonymData, word).substring(getSynonymLine
        (synonymData, word).indexOf('|') + 2,
         getSynonymLine(synonymData, word).length());

        String[] allasynonymer = nysynonymline.split(", ", nysynonymline.length());

        for(int i = 0; i < allasynonymer.length; i++){
            if (allasynonymer[i].compareToIgnoreCase(synonym) == 0)
            {
                String str = synonymline.substring
                (0, synonymline.indexOf(synonym));//gör en string med alla synonymer fram 
                String str2 = synonymline.substring           //till den som ska bort   
                (synonymline.lastIndexOf(synonym) + synonymlength + 2, synonymline.length());//gör en string med alla
                                                                        //alla synonymer efter den som ska bort
                String str3 = str + str2;   //lägg ihop de två föregående strings
        
                synonymData[index] = str3; //byt ut den gamla synonymline med det nya 
            }
            else if(allasynonymer[i].compareToIgnoreCase(synonym) != 0 && i+2 == allasynonymer.length){
                throw new IllegalArgumentException( //om synonymen inte finns, illegalargument
		        synonym + " not present");
            }
        }
	}

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        // add code here
        String temp; //temporär string till selection sort algorithm

        for(int i = 0; i < strings.length; i++) //selection sort
        {
            for (int j = i + 1; j < strings.length; j++)
            {
                if (strings[i].compareToIgnoreCase(strings[j]) > 0)//jämför bokstävernas unicode
                {                                                  //om det första ordet är senare i alfabetet
                    temp = strings[i];                             //hamnar det på den tidigares plast och vice versa
                    strings[i] = strings[j];
                    strings[j] = temp;
                }
            }
        }
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine)
    {
	    // add code here
       String nysynonymline = synonymLine.substring(synonymLine.indexOf('|') + 2, synonymLine.length());
        //tar bort ursprungsordet + "| "
       String[] synonymer = nysynonymline.split(", ", nysynonymline.length());
        //delar upp synonymerna i en string[]
       String temp;

       for (int i = 0; i < synonymer.length; i++) //sorterar synonymerna
       {
            for (int j = i + 1; j < synonymer.length; j++)
            {
                if (synonymer[i].compareToIgnoreCase(synonymer[j]) > 0)
                {
                    temp = synonymer[i];
                    synonymer[i] = synonymer[j];
                    synonymer[j] = temp;
                }
            }
       }
       String newstring = "";

       newstring = synonymLine.substring(0, synonymLine.indexOf('|') + 2) 
       + Arrays.toString(synonymer).replace("[", " ").replace("]", " ").trim();
       //lägger ihop ordet som synonymlistan kommer ifrån med synonymerna i sorterad ordning
       synonymLine = newstring;
       return synonymLine;


	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        // add code here
        for (int i = 0; i < synonymData.length; i++)
        {
            synonymData[i] = sortSynonymLine(synonymData[i]); //sortera synonymline
        }
        sortIgnoreCase(synonymData); //sortera första orden
        
	}
}