using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Customer_Relationship_Manager
{
    class Transaction
    {
        public Client Client { get; }
        public DateTime TransactionDate { get; }
        private List<Product> productsOrdered;

        public Transaction(Client client)
        {
            Client = client;
            TransactionDate = DateTime.Today;
            productsOrdered = new List<Product>();
        }

        public void AddProduct(Product product)
        {
            productsOrdered.Add(product);
        }
    }
}
