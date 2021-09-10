import io.bretty.console.table.*;

class Help {
  public static void table(Integer k, String[] args) {
	  String[] name = new String[k];
	  name=args;
	  String value="";
	  String[] ages;
	  ColumnFormatter<String> nameFormatter = ColumnFormatter.text(Alignment.LEFT, 15);
	  ColumnFormatter<String> valueFormatter = ColumnFormatter.text(Alignment.CENTER, 15);
	  Table.Builder builder = new Table.Builder("User/Comp", names, nameFormatter);
	  for (int i=0; i<k;i++) {
		  ages = new String[k];
		  for (int j=0;j<k;j++) {
		  int q = Winner.calculate(i, j, k);
		  if (q == 1) {value="WIN ";}
		  if (q == 0) {value="DRAW";}
		  if (q ==-1) {value="LOSE";}
		  ages[j] = value;
		  }
		  builder.addColumn(args[i], ages, valueFormatter);
	  }
	  Table table = builder.build();
	  System.out.println(table); 
}}
