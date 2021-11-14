import java.util.*;
/**
 * 
 * @author A
 * This entire class helps to faciliate printing neat and tidy tables for our contents
 */
public class TableGenerator {
	/**
	 * int PADDING_SIZE is the space between its content and its border
	 */
    private int PADDING_SIZE = 2;
    /**
     * String NEW_LINE prints a new line
     */
    private String NEW_LINE = "\n";
    /**
     *  String TABLE_JOINT_SYMBOL prints the symbol joining the columns
     */
    private String TABLE_JOINT_SYMBOL = "+";
    /**
     * String TABLE_V_SPLIT_SYMBOL prints the symbol splitting the columns
     */
    private String TABLE_V_SPLIT_SYMBOL = "|";
    /**
     * String TABLE_H_SPLIT_SYMBOL prints the symbol splitting the rows between the header and the data
     */
    private String TABLE_H_SPLIT_SYMBOL = "-";
    
    /**
     * This method generates the Table which holds the data
     * @param headersList - gets the content of the headers
     * @param rowsList - gets the content of the row's details
     * @param overRiddenHeaderHeight - overriding the HeaderHeight to not exceed more than 1 line
     * @return returns String of mutable sequence of characters
     */
    public String generateTable(List<String> headersList, List<List<String>> rowsList,int... overRiddenHeaderHeight)
    {
        StringBuilder stringBuilder = new StringBuilder();

        int rowHeight = overRiddenHeaderHeight.length > 0 ? overRiddenHeaderHeight[0] : 1; 

        Map<Integer,Integer> columnMaxWidthMapping = getMaximumWidhtofTable(headersList, rowsList);

        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
        stringBuilder.append(NEW_LINE);


        for (int headerIndex = 0; headerIndex < headersList.size(); headerIndex++) {
            fillCell(stringBuilder, headersList.get(headerIndex), headerIndex, columnMaxWidthMapping);
        }

        stringBuilder.append(NEW_LINE);

        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);


        for (List<String> row : rowsList) {

            for (int i = 0; i < rowHeight; i++) {
                stringBuilder.append(NEW_LINE);
            }

            for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                fillCell(stringBuilder, row.get(cellIndex), cellIndex, columnMaxWidthMapping);
            }

        }

        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);

        return stringBuilder.toString();
    }
    /**
     * This method deals with filling the space between the data
     * @param stringBuilder - gets the mutable sequence of characters
     * @param length - gets the length of the padding between the Strings of the data
     */
    private void fillSpace(StringBuilder stringBuilder, int length)
    {
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
    }
    /**
     * This method deals with the creation of individual rows of the table
     * @param stringBuilder - gets the mutable sequence of characters 
     * @param headersListSize - gets the size of the Headers List
     * @param columnMaxWidthMapping - gets the maximum width of the column 
     */
    private void createRowLine(StringBuilder stringBuilder,int headersListSize, Map<Integer,Integer> columnMaxWidthMapping)
    {
        for (int i = 0; i < headersListSize; i++) {
            if(i == 0)
            {
                stringBuilder.append(TABLE_JOINT_SYMBOL);   
            }

            for (int j = 0; j < columnMaxWidthMapping.get(i) + PADDING_SIZE * 2 ; j++) {
                stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
            }
            stringBuilder.append(TABLE_JOINT_SYMBOL);
        }
    }

    /**
     * This method deals with the maximum width of the table
     * @param headersList - gets the ArrayList of headers
     * @param rowsList - gets the content of rowsList
     * @return the interface of objects that maps keys to values
     */
    private Map<Integer,Integer> getMaximumWidhtofTable(List<String> headersList, List<List<String>> rowsList)
    {
        Map<Integer,Integer> columnMaxWidthMapping = new HashMap<>();

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
            columnMaxWidthMapping.put(columnIndex, 0);
        }

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {

            if(headersList.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex))
            {
                columnMaxWidthMapping.put(columnIndex, headersList.get(columnIndex).length());
            }
        }


        for (List<String> row : rowsList) {

            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {

                if(row.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex))
                {
                    columnMaxWidthMapping.put(columnIndex, row.get(columnIndex).length());
                }
            }
        }

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {

            if(columnMaxWidthMapping.get(columnIndex) % 2 != 0)
            {
                columnMaxWidthMapping.put(columnIndex, columnMaxWidthMapping.get(columnIndex) + 1);
            }
        }


        return columnMaxWidthMapping;
    }
    /**
     * This method deals with the optimal cell padding for the table
     * @param cellIndex - gets the index of the cell
     * @param datalength - the length of the individual data
     * @param columnMaxWidthMapping - the maximum width of the column
     * @param cellPaddingSize - the size of the individual Padding Cell
     * @return the int of optimum cell padding
     */
    private int getOptimumCellPadding(int cellIndex,int datalength,Map<Integer,Integer> columnMaxWidthMapping,int cellPaddingSize)
    {
        if(datalength % 2 != 0)
        {
            datalength++;
        }

        if(datalength < columnMaxWidthMapping.get(cellIndex))
        {
            cellPaddingSize = cellPaddingSize + (columnMaxWidthMapping.get(cellIndex) - datalength) / 2;
        }

        return cellPaddingSize;
    }
    /**
     * The method deals with the filling of the cell with data
     * @param stringBuilder - gets the stringBuilder String
     * @param cell - gets the String of individual data
     * @param cellIndex - the index of the cell
     * @param columnMaxWidthMapping - gets the max width for mapping
     */
    private void fillCell(StringBuilder stringBuilder,String cell,int cellIndex,Map<Integer,Integer> columnMaxWidthMapping)
    {

        int cellPaddingSize = getOptimumCellPadding(cellIndex, cell.length(), columnMaxWidthMapping, PADDING_SIZE);

        if(cellIndex == 0)
        {
            stringBuilder.append(TABLE_V_SPLIT_SYMBOL); 
        }

        fillSpace(stringBuilder, cellPaddingSize);
        stringBuilder.append(cell);
        if(cell.length() % 2 != 0)
        {
            stringBuilder.append(" ");
        }

        fillSpace(stringBuilder, cellPaddingSize);

        stringBuilder.append(TABLE_V_SPLIT_SYMBOL); 

    }
    /**
     * This method deals with the printing of the table
     * @param m - gets the ArrayList of MenuItems
     * @param a - gets the number from which the MenuItems are printed in the Menu
     */
    public static void printtable(ArrayList<MenuItems> m,int a) {
        TableGenerator tableGenerator = new TableGenerator();

        List<String> headersList = new ArrayList<>(); 
        headersList.add("Index");
        headersList.add("Name");
        headersList.add("Price");
        headersList.add("Description");
        if(a!=0)
        {
        	a=0;
        }
        List<List<String>> rowsList = new ArrayList<>();
        //List<String> row = new ArrayList<>(); 
        //List<MenuItems> m=mainthingy.geto();
        
        for (int i = a; i < m.size() ; i++) {
        	List<String> row = new ArrayList<>(); 
        	
        	String ii=String.valueOf(i+1);
        	//System.out.println(ii);
            String s=m.get(i).getName();
            //System.out.println(s);
            //row.add(row.get(i).getName());//UUID.randomUUID().toString());
            String p=String.valueOf(m.get(i).getPrice());
            //System.out.println(p);
            //row.add(p);//UUID.randomUUID().toString());
            String s1=m.get(i).getDescription();
            //System.out.println(s1);
            
            row.add(ii);
            row.add(s);
            row.add(p);
            row.add(s1);//UUID.randomUUID().toString());
            //row.add("Test 4");//UUID.randomUUID().toString());

            rowsList.add(row);
        }
        /*for(int i=0;i<2;i++)
        {
        	row.add("string");
        	row.add("test");
        	rowsList.add(row);
        }*/

        System.out.println(tableGenerator.generateTable(headersList, rowsList));
    }
    /**
     * This method deals with the printing of individual MenuItems of package contents
     * @param m - gets the ArrayList of MenuItems
     * @param name - gets the name of the Package whose contents are printed
     */
    public static void printpackagecontents(ArrayList<MenuItems> m,String name) {
        TableGenerator tableGenerator = new TableGenerator();
        
        List<String> headersList = new ArrayList<>(); 
        headersList.add("Index");
        headersList.add(name+" contents");
        headersList.add("Price");
        headersList.add("Description");
        headersList.add("Type");
        
        List<List<String>> rowsList = new ArrayList<>();
        //List<String> row = new ArrayList<>(); 
        //List<MenuItems> m=mainthingy.geto();
        
        for (int i = 0; i < m.size() ; i++) {
        	List<String> row = new ArrayList<>(); 
        	
        	String ii=String.valueOf(i+1);
        	//System.out.println(ii);
            String s=m.get(i).getName();
            //System.out.println(s);
            //row.add(row.get(i).getName());//UUID.randomUUID().toString());
            String p=String.valueOf(m.get(i).getPrice());
            //System.out.println(p);
            //row.add(p);//UUID.randomUUID().toString());
            String s1=m.get(i).getDescription();
            //System.out.println(s1);
            String s2=m.get(i).getType();
            row.add(ii);
            row.add(s);
            row.add(p);
            row.add(s1);//UUID.randomUUID().toString());
            row.add(s2);
            //row.add("Test 4");//UUID.randomUUID().toString());

            rowsList.add(row);
        }
        /*for(int i=0;i<2;i++)
        {
        	row.add("string");
        	row.add("test");
        	rowsList.add(row);
        }*/

        System.out.println(tableGenerator.generateTable(headersList, rowsList));
    }
    /**
     * This method deals with the printing of the invoice
     * @param m - gets the ArrayList of MenuItems
     * @param price - gets the corresponding price of the said items (total price of the items)
     */
    public static void printinvoice(ArrayList<MenuItems> m,ArrayList<Double> price) {
        TableGenerator tableGenerator = new TableGenerator();
        System.out.println("----------------------------------------");
        List<String> headersList = new ArrayList<>(); 
        headersList.add("Index");
        headersList.add("Name");
        headersList.add("Quantity");
        headersList.add("Price");
        
        
        List<List<String>> rowsList = new ArrayList<>();
        //List<String> row = new ArrayList<>(); 
        //List<MenuItems> m=mainthingy.geto();
        MenuItems t; 
        for (int i = 0; i < m.size() ; i++) {
        	List<String> row = new ArrayList<>(); 
        	//t= MainMenu.searchByName(m.get(i).getName());
        	t= MainMenuMgr.Menu.searchByName(m.get(i).getName());
        	double one=t.getPrice();
        	int q=(int) (price.get(i)/one);
        	String index=String.valueOf(i+1);
        	//System.out.println(ii);
            String name=m.get(i).getName();
            //System.out.println(s);
            //row.add(row.get(i).getName());//UUID.randomUUID().toString());
            String quantity=String.valueOf(q);
            //System.out.println(p);
            //row.add(p);//UUID.randomUUID().toString());
            String p=String.valueOf(one);
            //System.out.println(s1);
            //String s2=m.get(i).getType();
            row.add(index);
            row.add(name);
            row.add(quantity);
            row.add(p);//UUID.randomUUID().toString());
            
            //row.add("Test 4");//UUID.randomUUID().toString());

            rowsList.add(row);
        }
        /*for(int i=0;i<2;i++)
        {
        	row.add("string");
        	row.add("test");
        	rowsList.add(row);
        }*/

        System.out.println(tableGenerator.generateTable(headersList, rowsList));
        System.out.println("----------------------------------------");
    }
}