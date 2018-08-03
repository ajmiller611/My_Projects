using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Customer_Relationship_Manager
{
    class Product
    {
        public int ProductID { get; set; }
        public string ProductName { get; set; }
        public decimal Price { get; set; }
        public string Description { get; set; }

        public Product()
        {

        }

        public Product(int productID, string productName, decimal price, string description)
        {
            ProductID = productID;
            ProductName = productName;
            Price = price;
            Description = description;
        }
    }
}
