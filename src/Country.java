import java.util.Iterator;

/**
 * @class Country[contains four private instance variables: name, linkedList of SubscriptionYear, minYear and maxYear]
 *  
 */
public class Country
{
    /**
     * @member name[Type: String, for storing the name of the country]
     * @member subscriptions[Type: 1D array of type SubscriptionYear, for holding all the subscription data for a country]
     * @member minYear[Type: int, for tracking value of SubscriptionYear's minimum year]
     * @member maxYear[Type: int, for tracking value of SubscriptionYear's maximum year]
     */
    private String name;
    private LinkedList<SubscriptionYear> subscriptions;
    private int minYear;
    private int maxYear;
//    private double minSubscription;
//    private double maxSubscription;
    
    /**
     * New constructor that takes in one parameter, to be used to create a temporary Country object containing name of the Country] 
     * @param countryName[an object of type String, which is the name of the country]
     */
    public Country(String countryName)
    {
        this.name = countryName;
        
        // make a new list of SubscriptionsYear
        subscriptions = new LinkedList<SubscriptionYear>();
        
        // initializes min to a large number 
        this.minYear = 9999;
        
        // initializes max to a small number
        this.maxYear = 0;
        
//        this.maxSubscription = 0.0;
//        
//        this.minSubscription = 999.9;
        
    }

//    old constructor with two parameters 
//    public Country(String countryName, int numOfYears)
//    {
//        this.name = countryName;
//        this.subscriptions = new SubscriptionYear[numOfYears];
//    }
    
    /**
     * @override Object.equlas() to check if two objects of type Country are equal, based on their name
     * @return boolean
     */
    public boolean equals(Object someObject)
    {   
        // if someObject is null, simply return
        if (someObject == null)
            return false;
       
        // else checks if name of calling object, ignore case equals object passed in
        // someOject is of type Object, so it is casted in to Country before comparing it with Country object 
        return (this.name.equalsIgnoreCase(((Country) someObject).getName()));                                  
    }   

    /**
     * Takes in two parameters and uses it to create a new SubscriptionYear object
     * Saves SubscriptionYear object in subscriptions list
     * updates the minimum and maximum year
     * @param year[gets stored in subscriptions list as SubscriptionYear "year"] 
     * @param subscriptionData[gets stored in subscriptions list as SubscriptionYear "subscription"]
     */
    public void addSubscriptionYear(int year, double subscriptionData)
    {
        SubscriptionYear currentYearData = new SubscriptionYear(year, subscriptionData);
        this.subscriptions.add(currentYearData); 
        
        // update the minYear as minimum year
        if (year < minYear)        
            minYear = year;
     
        // update the maxYear as the maximum year, each time new node is added to list
        else if (year > maxYear)        
            maxYear = year;
    }
    
    public double getMinSubscription()
    {
        return subscriptions.getNodeAtIndex(0).getData().getSubscriptions();      
    }
    
    public double getMaxSubscription()
    {
        return subscriptions.getNodeAtIndex(subscriptions.size()-1).getData().getSubscriptions();      
    }

    /**
     * Takes in two parameters of type int 
     * check if the requested subscription period is valid using "minYear" and "maxYear"
     * Using an iterator calculates total number of subscriptions for a specified period
     * @param startYear[holds the value passed by user every time method is called and gets updated as the subscriptions years' minimum year]
     * @param endYear[holds the value passed by user every time method is called and gets updated as the subscriptions years' maximum year ]
     * @return total number of subscriptions for a specified period
     */
    public double getNumSubscriptionsForPeriod(int startYear, int endYear) 
    {
        double totalSubscriptions = 0;

        if (startYear < minYear)
        {
            System.out.println("Please enter a valid year. The starting year "
                  + "is less than " + minYear);
            
         // updates the start year
          startYear = minYear;
          
          System.out.println("Starting from "+ startYear + " total number of subscriptions ");          
        }
        
        else if (startYear > maxYear)
        {
            System.out.println("Please enter valid years. The requested years are out of range");
            return 0;
        }

        else if (endYear < minYear) 
        {
            System.out.println("Please enter valid years. The requested years are out of range");
            return 0;
        } 
        
        else if (endYear > maxYear) 
        {
            System.out.println("Please enter a valid year. The end year "
                    + "is greater than " + maxYear);

            // updates end year
            endYear = maxYear;

            System.out.println("Total number of subscriptions till " + endYear); 
        }

        // using an iterator to get the subscriptions data for specified period
        Iterator<SubscriptionYear> iterator = subscriptions.iterator();
        
        SubscriptionYear current;
        
        int i = 0;
        
        while (iterator.hasNext())
        {
            current = subscriptions.getNodeAtIndex(i).getData();
            
            if (current.getYear() == startYear)
            {
                while(current != null)
               {
                    current = iterator.next();
                    totalSubscriptions += current.getSubscriptions();
                    
                    if (current.getYear() == endYear)
                       break;                    
               }
                break;
            }
        i++;
               
         }
             
     return totalSubscriptions;
    }

    /**
     * overrides subscription.toString()
     * @return String of country data, containing its name and subscription data
     */
    public String toString()
    {
        String countryName = this.getName();
        
        String totalCountryData = " ";
   
        Iterator<SubscriptionYear> iterator = subscriptions.iterator();
        
        while (iterator.hasNext()) 
        {
            totalCountryData += iterator.next();  
        } 
        return countryName + '\n' + totalCountryData + '\n';
    }
    
    public String getName() { return name; }

    public LinkedList<SubscriptionYear> getSubscriptions() { return subscriptions; }  
    
    public int getMaxYear() { return this.maxYear; }
    
    public int getMinYear() { return this.minYear; }
  
}            



