package domainhub;
import java.io.*;
import java.lang.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URI;
import java.awt.Desktop;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.Random;

  class address 
{      
       public int[] oct = new int[256];
       char g;
}
class Record
{
    public String rec1;
    public String rec2;
    Record(String str1,String str2)
    {
        this.rec1=str1;
        this.rec2=str2;
    }
}

class domain_hub
{
    public String dm_nm;
    public String ip;
    public static int a=1,b=128,c=192,d=224;
    public domain_hub next;
    //public domain_hub ptr;
    domain_hub()
    {
        next=null;
    }
    domain_hub(String dm,String ipp )
    { 
        this.dm_nm=dm;
        this.ip=ipp;
        next=null;
    }
   /* public void write_to_file(domain_hub p,server s)
    {
     
    }
         catch(Exception e){System.out.println(e);}  
    }*/
    public void write_to_file(domain_hub p)
    {
        BufferedWriter bw = null;
    try 
    {
       // APPEND MODE SET HERE
      bw = new BufferedWriter(new FileWriter("wr_file.txt", true));
      bw.write(p.dm_nm);
      bw.write(" ");
      bw.write(p.ip);
      
      bw.newLine();
      bw.flush();
    }
     catch (IOException ioe) 
    {
       ioe.printStackTrace();
    } 
    
    finally 
    {// always close the file
       if (bw != null) try {
       bw.close();
      } catch (IOException e) {
          System.out.println(e);
       // just ignore it
       }
    }
    }       //end of write to file function 
    
    public void read_from_file(domain_hub p)
    {
         try
         {  
           FileInputStream fin=new FileInputStream("wr_file.txt");  
           int i=0;  
           while((i=fin.read())!=-1)
           {  
           System.out.print((char)i);  
           }  
           fin.close();  
        }
         catch(Exception e){System.out.println(e);}  
    }
    
    public void insert_new_domain(String domain1,String ipp,domain_hub root)
    {
        domain_hub newnode=new domain_hub(domain1,ipp);

      //read_from_file(root);
      domain_hub t=root;
      System.out.println("newnode=" + newnode.dm_nm);
     // if(t.ptr==null) 
      if(t==null)
      {
            System.out.println("yes");
            //t.ptr= newnode;
            t=newnode;
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("wr_file.txt", true));
            } catch (IOException ex) {
                Logger.getLogger(domain_hub.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bw.write(newnode.dm_nm);
            } catch (IOException ex) {
                Logger.getLogger(domain_hub.class.getName()).log(Level.SEVERE, null, ex);
            }
           // bw.write(newnode.ip);
            //write_to_file(root);
            return;
      }
      System.out.println("no");
     // t = t.ptr;


      while(t.next!=null)
      t=t.next;
      
      t.next=newnode;
      write_to_file(newnode);
    }
    
    public int find(String dm ,domain_hub ptr)
    {
       // domain_hub tptr=ptr;
         System.out.println("finding domain name"+dm);
        try {
            File file = new File("wr_file.txt");
            Scanner scanner = new Scanner(file);

    //now read the file line by line...
    int lineNum = 0;
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lineNum++;
        // System.out.println(s1.compareTo(s2));//0  
        String[] tokens = line.split(" ");
        Record record = new Record(tokens[0],tokens[1]);//process record , etc

        if(record.rec1.compareTo(dm)==0 ) { 
            System.out.println("yes finded " +lineNum + "ip address is " + record.rec2);
            return 1;
        }
        System.out.println("line number = " +lineNum +line );
    }
    
    System.out.println("not find");
} 
    catch(FileNotFoundException e) { 
    System.out.println(e);
    //handle this
}
     return 0;
    }
    
    
 }//end of class domain hub
       

abstract class client
{
      public String name;
      public String phone_num;
      public String email;
      abstract  int get_inquiry();
};


//beginning of class visitor
class visitor extends client
{
       public String domain;
       public String domain_category;
       public String ip;
       public String username;
       public String password;
       public static int a=1,b=128,c=192,d=224;
       @Override
       public int get_inquiry()
       {
           try {
               InputStreamReader r=new InputStreamReader(System.in);
               BufferedReader br=new BufferedReader(r);
               System.out.println("\n\nPlease enter your contact details\n\n");
               System.out.println("Enter your full name");
               name=br.readLine();
               System.out.println("Enter your phone number");
               phone_num=br.readLine();
               System.out.println("Enter your email address");
               email=br.readLine();
           }
           catch (IOException ex) {
               Logger.getLogger(visitor.class.getName()).log(Level.SEVERE, null, ex);
           }
           return 0;
       }
       public void get_required_dom_details()
       {
           try {
               System.out.println("Enter the category for the domain you want..\n\n \t You can choose from the below categories:\n \t\t ( .com )\t (.org )\n( .gov )\t ( .buzz )\n ( .edu )\t ( .in ) \n ( .uk ) \t ( .co.in )\n\n");
               InputStreamReader r=new InputStreamReader(System.in);
               BufferedReader br=new BufferedReader(r);
               domain_category=br.readLine();
               System.out.println("\n\nPlease enter the Fully Qualified Domain name of Required domain:\n\n");
               domain=br.readLine();
               //instruction to find where domain name is unique it is unique then generate ip address and add to file 
               domain=domain+domain_category;
               System.out.println("domain name=" + domain);
               File file = new File("wr_file.txt");
               Scanner scanner = new Scanner(file);

    //now read the file line by line...
          int lineNum = 0;
        while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lineNum++;
        // System.out.println(s1.compareTo(s2));//0  
        String[] tokens = line.split(" ");
        Record record = new Record(tokens[0],tokens[1]);//process record , etc

        if(record.rec1.compareTo(domain)==0 ) { 
           // System.out.println("yes finded " +lineNum + "ip address is " + record.rec2);
            System.out.println("Domain name selected by you already exist");
            return ;
        }
        //System.out.println("line number = " +lineNum +line );
    }
    
       //System.out.println("not find");
        System.out.println("Your domain name gets registered");
        System.out.println("Welcome to ip classe addresses");
        InputStreamReader rr=new InputStreamReader(System.in);
        BufferedReader brr=new BufferedReader(rr);
        int  count=0;
        int oct1[]=new int[256];
        int oct2[]=new int[256];
        int oct3[]=new int[256];
        int oct4[]=new int[256];
        //int a=1,b=128,c=192,d=224;
        do
        {    
            count++;
        System.out.println("\nA:for class A 1to 126 ip addresses");
        System.out.println("B:for class B ip addresses");
        System.out.println("C:for class C ip addresses");
        System.out.println("D:for class D ip addresses"); 
        String choice=brr.readLine();
        Random rand =new Random();
        switch(choice)
        {
            case "A":
                System.out.println("hello you are in class A");
                do
                {
                     a = rand.nextInt(( 127- 1) + 1) + 1;
                     b = rand.nextInt(( 255- 1) + 1) + 1;
                     c = rand.nextInt(( 255- 1) + 1) + 1;
                     d = rand.nextInt(( 255- 1) + 1) + 1;
                }
                while(oct1[a]==1 && oct2[b]==1 && oct3[c]==1 && oct4[d]==1);
                oct1[a]=1;oct2[b]=1;oct3[c]=1;oct4[d]=1;
                    System.out.println("random no is =" + a + b + c + d);
                ip= a  +  "." +b+ "." +c+ "." + d;
                return;
            case "B":
                System.out.println("hello you are in class B");
                do
                {
                     a = rand.nextInt(( 191- 128) + 1) + 128;
                     b = rand.nextInt(( 255- 1) + 1) + 1;
                     c = rand.nextInt(( 255- 1) + 1) + 1;
                     d = rand.nextInt(( 255- 1) + 1) + 1;
                }
                while(oct1[a]==1 && oct2[b]==1 && oct3[c]==1 && oct4[d]==1);
                oct1[a]=1;oct2[b]=1;oct3[c]=1;oct4[d]=1;
                ip=a  +  "." +b+ "." +c+ "." + d;
                return;
            case "C":
                System.out.println("hello you are in class C");
                do
                {
                     a = rand.nextInt(( 223- 192) + 1) + 192;
                     b = rand.nextInt(( 255- 1) + 1) + 1;
                     c = rand.nextInt(( 255- 1) + 1) + 1;
                     d = rand.nextInt(( 255- 1) + 1) + 1;
                }
                while(oct1[a]==1 && oct2[b]==1 && oct3[c]==1 && oct4[d]==1);
                oct1[a]=1;oct2[b]=1;oct3[c]=1;oct4[d]=1;
                ip= a  +  "." +b+ "." +c+ "." + d;
                return;
            case "D":
                System.out.println("hello you are in class D");
                do
                {
                     a = rand.nextInt(( 239- 224) + 1) + 224;
                     b = rand.nextInt(( 255- 1) + 1) + 1;
                     c = rand.nextInt(( 255- 1) + 1) + 1;
                     d = rand.nextInt(( 255- 1) + 1) + 1;
                }
                while(oct1[a]==1 && oct2[b]==1 && oct3[c]==1 && oct4[d]==1);
                oct1[a]=1;oct2[b]=1;oct3[c]=1;oct4[d]=1;
                ip = a  +  "." +b+ "." +c+ "." + d;
                return;
            default:
                  if(count>2)
                  {
                      System.out.println("You have entered wrong inputs contact admin");
                      return;
                  }
                  if(count==2)
                  {
                      System.out.println("\nThis will be last attempt... Give correct input\n");
                  }
                  else
                  System.out.println("Wrong choice...\nPlease try again");
                  break;
                
        }
        }
        while(true);
                
        //System.out.println("Domain name entered by you is available");
        //generate ip address
               
               } 
               catch (IOException ex) {
               Logger.getLogger(visitor.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
              
       public void purchase(visitor v,server s)
       {
           
       }
};
//end of class visitor


//beginning of class Server
class server
{
      String domain;
      String ip;
      public domain_hub dh=new domain_hub();              //composition of domain hub in server class "has a" relationship..
      public int is_available(String name)
      {
          System.out.println("debugging"+name);
          if(dh.find(name,dh)==1)
          {
              System.out.println("hello");
              return 0;   
          }
          else
          {
              System.out.println("not available");
              return 1;
          }
              
      }
      public void  get_domain(String ipp)
      {
          
      }
      public void get_ip(String name)
      {
          if(dh.find(name,dh)==0)
              System.out.println(name + "is invalid domain name");
              
              
                      
      }
      public void purchase(visitor v,server s)
      {
          System.out.println("In purchase"+v.domain);
          BufferedWriter bw = null;
    try 
    {
       // APPEND MODE SET HERE
      bw = new BufferedWriter(new FileWriter("wr_file.txt", true));
      bw.write(v.domain);
      bw.write(" ");
      
      bw.write(v.ip);
      
      bw.newLine();
      bw.flush();
    }
     catch (IOException ioe) 
    {
       ioe.printStackTrace();
    } 
    
    finally 
    {// always close the file
       if (bw != null) try {
       bw.close();
      } catch (IOException e) {
          System.out.println(e);
       // just ignore it
       }
    }
          System.out.println("End purchase");
      }
};


//A class for login and  method to record further details
class login
{
     public int level;
     public String name;
     public String username;
     public String password;
     int flag=0;//flag who have already have login id
             // void purchase(visitor *v, server *s);
     public void get_details()
     {
         try {
             InputStreamReader r=new InputStreamReader(System.in);
             BufferedReader br=new BufferedReader(r);
             System.out.println("For Login Purpose..\n\nEnter your name\n\n");
             //getting login details
             name=br.readLine();
             System.out.println("Enter new username\n");
             username=br.readLine();
             System.out.println("Enter new Password\n");
             password=br.readLine();
             //write to login form
             BufferedWriter bw = null;
             try 
             {
               // APPEND MODE SET HERE
               bw = new BufferedWriter(new FileWriter("login_file.txt", true));
               bw.write(username);
               bw.write(" ");
               bw.write(password);
               bw.newLine();
               bw.flush();
             }
            catch (IOException ioe) 
             {
               ioe.printStackTrace();
             } 
    
             finally 
             {// always close the file
               if (bw != null) try {
               bw.close();
             } catch (IOException e) {
                System.out.println(e);
                 // just ignore it
                 }
             }
             level=1;
         } 
         catch (IOException ex) {
             Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     //method to write to log file
     public void add_new_user()
     {
         get_details();
         level=2;
         BufferedWriter bw = null;
    try 
    {
       // APPEND MODE SET HERE
      bw = new BufferedWriter(new FileWriter("login_file.txt", true));
      bw.write(username);
      bw.write(" ");
      bw.write(password);
      bw.newLine();
      bw.flush();
    }
     catch (IOException ioe) 
    {
       ioe.printStackTrace();
    } 
    
    finally 
    {// always close the file
       if (bw != null) try {
       bw.close();
      } catch (IOException e) {
          System.out.println(e);
       // just ignore it
       }
    }
    
  }
      public int check_login()
      {
        get_details();
        try {
            File file = new File("login_file.txt");
            Scanner scanner = new Scanner(file);

                                                  //now read the file line by line...
              int lineNum = 0;
              while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                // System.out.println(s1.compareTo(s2));//0  
                String[] tokens = line.split(" ");
                Record record = new Record(tokens[0],tokens[1]);//process record , etc

                   if(record.rec1.compareTo(username)==0 && record.rec2.compareTo(password)==0) 
                   { 
                    //System.out.println("yes finded " +lineNum + "ip address is " + record.rec2);
                    return 1;
                   }
                  //System.out.println("line number = " +lineNum +line );
            }
    
    System.out.println("Invalid user name or password");
} 
    catch(FileNotFoundException e) { 
    System.out.println(e);
    //handle this
}
       return 0;
      }
};

class customer extends client
{
       public String username;
       public String password;
       public int level;
       public String domain;
       public String ip;
       public int flag;

       @Override
       public int get_inquiry()
       {
           login lo = new login();
           int k=lo.check_login();
           
           if(k==0){
           System.out.println("Incorrect Login Id/Password.\n\n");
           }
           if(k!=0)
           {
               System.out.println("Do you want to purchase  domain name \nenter 1:yes\nenter 2:no");
               Scanner keyboard = new Scanner(System.in);
               flag = keyboard.nextInt();
               
               if(flag==1)
               {
                   domain_hub ptr =new domain_hub();
                   try {
                       InputStreamReader r=new InputStreamReader(System.in);
                       BufferedReader br=new BufferedReader(r);
                       System.out.println("Enter unique domain address");
                       domain=br.readLine();
                       if(ptr.find(domain, ptr)==0)
                           System.out.println("Generating random ip address")
                           //generate random ip address and add to file 
                           //then write the to the file
                           ;
                       else
                           System.out.println("Domain name provide by you already exist");
                       
                   } catch (IOException ex) {
                       Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
                   }
                
               }
               
           }
           return k; 
       }
        public void get_my_info()
        {
            
            
            
        }
        public void reverse_resolving(String ip,server s)
        {
            try {
            File file = new File("wr_file.txt");
            Scanner scanner = new Scanner(file);

    //now read the file line by line...
       int lineNum = 0;
       while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lineNum++;
        // System.out.println(s1.compareTo(s2));//0  
        String[] tokens = line.split(" ");
        Record record = new Record(tokens[0],tokens[1]);//process record , etc

        if(record.rec2.compareTo(ip)==0 ) { 
            System.out.println("yes finded " +lineNum + "domain address is " + record.rec1);
            return ;
        }
        System.out.println("line number = " +lineNum +line );
    }
    
    System.out.println("Invalid ip address");
} 
    catch(FileNotFoundException e) { 
    System.out.println(e);
    //handle this
}
        }
        public void get_ip(String dm_nm,server s)
        {
            try {
            File file = new File("wr_file.txt");
            Scanner scanner = new Scanner(file);

    //now read the file line by line...
    int lineNum = 0;
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lineNum++;
        // System.out.println(s1.compareTo(s2));//0  
        String[] tokens = line.split(" ");
        Record record = new Record(tokens[0],tokens[1]);//process record , etc

        if(record.rec1.compareTo(dm_nm)==0 ) { 
            System.out.println("yes finded " +lineNum + "ip address is " + record.rec2);
            return;
        }
        System.out.println("line number = " +lineNum +line );
    }
    
    System.out.println("Invalid domain address");
} 
    catch(FileNotFoundException e) { 
    System.out.println(e);
    //handle this
}
         
        }
};





    public class DomainHub {
       public static void main(String[] args)
     {
         
         main_menu();
       // domain_hub dHub1 = new domain_hub("utpal","123.456");
       // dHub1.write_to_file(dHub1);
       // dHub1.read_from_file(dHub1);
       // dHub1.insert_new_domain("Abhi", "999.998", dHub1);
       // dHub1.insert_new_domain("Pari","88888.8888",dHub1);
       // dHub1.read_from_file(dHub1);
       // System.out.println("calling find function");
       // if(dHub1.find("Parjki",dHub1)==1)
          //  System.out.println("finded");
        //else
           // System.out.println("not find");
    }
       
      public static void visitor_menu()
{
     //system("cls");
     System.out.println("\n\n\t-----------------------------------------------------------\n");
     System.out.println("\t******* Welcome to BIG Domain HUB  **********\n");
     System.out.println("\t** A DNS, ISP Services and DNS Tutorial Portal  ****\n");
     System.out.println("\t**** Welcome Visitor  ****\n");
     System.out.println("\t---------------------------------------------------------------\n");
     char ch='x';
     int i,flag=0;
     visitor v=new visitor();
     server s=new server();
     while(!(ch == '1'|| ch=='2' ||ch=='3'|| ch=='4'))
     {
         try {
             System.out.println("\n************* Select the service from the list *****************\n");
             System.out.println("---------------------------------------------------------------------------\n");
             System.out.println("\t1. Search for a domains' availability");
             System.out.println("\t2. Purchase a New Domain");
             System.out.println("\t3. Main Menu.");
             System.out.println("\t4. Exit.");
             System.out.println("\t5.To open a website.");
             System.out.println("\t6.To get IP address from domain name.");
             System.out.println("\t7.To get domain name from IP address.\n");
             System.out.println("\n--------------------------------------------------------------------------\n");
             System.out.println("Visitor please enter the correct choice..\n");
             
             Scanner reader = new Scanner(System.in);
             ch = reader.next().charAt(0);

             switch(ch)
             {
                 case '1':
                     int choice;
                     v.get_inquiry();
                     System.out.println("hello calling visitor claa required domain details");
                     v.get_required_dom_details();
                     System.out.println("checking availability"+ v.domain);
                     i = s.is_available(v.domain);
                     if(i==1)
                     {
                         flag=1;
                         System.out.println("\tThe domain is available. Do you want to purchase  it? \n");
                         System.out.println("\t1. Yes\n\t 2. No, Go to Main-Menu\n");
                         Scanner in = new Scanner(System.in);
                         choice = in.nextInt();
                         if(choice==1)
                         {
                             System.out.println("going for purchasing");
                             s.purchase(v,s);
                             
                             System.out.println("\nPress any key to go to main menu\n");
                             choice=in.nextInt();
                             main_menu();
                         }
                         else
                             ;
                         main_menu();
                     }
                     else
                     {
                         System.out.println("\n\tSorry Visitor, The required domain is not availble in this particular category.\n");
                         main_menu();
                     }
                     break;
                     
                 case '2':
                     if(flag==0)
                     {
                         v.get_inquiry();
                         v.get_required_dom_details();
                     }
                     s.purchase(v,s);
                     
                     main_menu();
                     break;
                     
                 case '3':
                     main_menu();
                     break;
                 case '4':
                     System.exit(0);
                     break;
                 case '6':
                     System.out.println("Enter domain name to open web page.");
                     InputStreamReader rr=new InputStreamReader(System.in);
                     BufferedReader brr=new BufferedReader(rr);
                     System.out.println("Enter unique domain address (www.domain.com) format to get IP address");
                     String domm=brr.readLine();
                     InetAddress host=InetAddress.getByName(domm);
                     System.out.println("IP addresses ="+host.getHostAddress());
                     break;
                 case '7': 
                     System.out.println("Enter domain name to open web page.");
                     InputStreamReader ra=new InputStreamReader(System.in);
                     BufferedReader S=new BufferedReader(ra);
                     System.out.println("Enter unique IP address (0.0.0.0) format to get domain name");
                     String d=S.readLine();
                     InetAddress hos=InetAddress.getByName(d);
                     System.out.println("Domain name  ="+hos.getHostName());
                     break;
                  default:   
                     System.out.println("Wrong choice. Please enter Again. \n");   
                      break;
                 case '5':
                     System.out.println("Enter domain name to open web page.");
                     InputStreamReader r=new InputStreamReader(System.in);
                     BufferedReader br=new BufferedReader(r);
                     System.out.println("Enter unique domain address (www.domain.com) format");
                     String dom=br.readLine();
                     Desktop de= Desktop.getDesktop();
                       { 
                          try {
                              //d.browse(new URI("http://www.nitt.edu"));
                              de.browse(new URI("http://"+dom));
                             }
                          catch (URISyntaxException ex) {
                                Logger.getLogger(DomainHub.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                      break;
                    }
                 }    catch (IOException ex)
                      {
                      Logger.getLogger(DomainHub.class.getName()).log(Level.SEVERE, null, ex);
                     }
     }
}
      
      public static void admin_menu()
      {
         System.out.println("\n\n\t-----------------------------------------------------------\n");
         System.out.println("\t******* Welcome to BIG Domain HUB  **********\n");
         System.out.println("In admin menu\n");
         System.out.println("\t** A DNS, ISP Services and DNS Tutorial Portal  ****\n");
         System.out.println("\t**** Welcome Admin Panel  ****\n");
         System.out.println("\t---------------------------------------------------------------\n")
                 ;
      }
      
      public static void customer_menu()
      {
          char ch='X';
          System.out.println("\n\n\t-----------------------------------------------------------\n");
          System.out.println("\t******* Welcom1e to BIG Domain HUB  **********\n");
          System.out.println("\t** A DNS, ISP Services and DNS Tutorial Portal  ****\n");
          System.out.println("\t**** Welcome to the Customer Care  ****\n");
          System.out.println("\t---------------------------------------------------------------\n");
          int k;
          //customer c=new customer();
        //  k=c.get_inquiry();
          /*if(k==0)
          {
             //System("pause");
             //System(clr);
             main_menu();
          }*/
         // while(!(ch == '1'|| ch=='2' ||ch=='3'|| ch=='4'))
           while(true)
           {
               System.out.println("Selsect  from the following care options\n\n");
               System.out.println("\n\n\t--------------------------------------------------\n");
               System.out.println("\t1.For log in.");
               System.out.println("\t2.For main menu");
               Scanner reader = new Scanner(System.in);
               ch = reader.next().charAt(0);
               switch(ch)
               {
                   case '1':
                       customer c=new customer();
                       k=c.get_inquiry();
                       if(k==1)
                           visitor_menu();
                       break;
                   case '2':main_menu();
                       break;
                   default:
                       System.out.println("Invalid input");
                       main_menu();
                       break;
                       
               }
               
           }
               
         
      }
      public static void main_menu()
      {
          System.out.println("\n\n\t-----------------------------------------------------------\n");
          System.out.println("\t******* Welcome to BIG Domain HUB  **********\n");
          System.out.println("\t** A DNS, ISP Services and DNS Tutorial Portal  ****\n");
          System.out.println("\t---------------------------------------------------------------\n");
          char ch='x';
          while(!(ch == '1'|| ch=='2' ||ch=='3'|| ch=='4'))
     {
             System.out.println("************* Select the Client Type from the list *****************\n");
             System.out.println("---------------------------------------------------------------------------\n");
             System.out.println("\t1. Visitor --> Can Inquire for Domain, Purchase.");
             System.out.println("\t2. Customer --> Can get facilities like Reverse resolving etc.");
             System.out.println("\t3. Admin --> Can do mining on DNS, can find popular domains.");
             System.out.println("\t4. Exit.\n");
             System.out.println("--------------------------------------------------------------------------\n");
             System.out.println("USER please enter the correct choice..\n");
             Scanner reader = new Scanner(System.in);
             ch = reader.next().charAt(0);
             switch(ch)
             {
                       case '1':
                            visitor_menu();
                            System.out.println("Selected visitor");
                            break;
                       case '2':
                            customer_menu();
                            System.out.println("Selected Customer");
                            break;
                       case '3':
                            admin_menu();
                            System.out.println("Selected Admin");
                            visitor_menu();
                            break;
                       case '4':
                            System.exit(0);
                            break;
                       default:
                           // System.out.println("Wrong choice. Please enter Again. ");
                              
                           try
                            {
                               final String os = System.getProperty("os.name");
                                    if (os.contains("Windows"))
                                      {
                                          Runtime.getRuntime().exec("cls");
                                      }
                                        else
                                      {
                                          Runtime.getRuntime().exec("clear");
                                      }
                            }
                            catch (final Exception e)
                             {
                                 System.out.println(e);
                             }
                       System.out.println("Wrong choice. Please enter Again. ");

             }
     }
      }
    
}
    