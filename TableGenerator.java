import java.util.*;
/**
 * 
 * @author A
 * THis entire class helps to faciliate printing neat and tide tables for our contents
 */
public class TableGenerator {

    private int PADDING_SIZE = 2;
    private String NEW_LINE = "\n";
    private String TABLE_JOINT_SYMBOL = "+";
    private String TABLE_V_SPLIT_SYMBOL = "|";
    private String TABLE_H_SPLIT_SYMBOL = "-";

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

    private void fillSpace(StringBuilder stringBuilder, int length)
    {
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
    }

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