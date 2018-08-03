using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Customer_Relationship_Manager
{
    class Client
    {
        //TODO create a client buying history class or database
        public string ClientName { get; private set; }
        public string CompanyName { get; private set; }
        public string PhoneNumber { get; private set; }
        public string Notes { get; private set; }
        public List<Transaction> ClientPurchaseHistory {get; }
        private Address address;

        public Client(string clientName, string companyName, string phoneNumber, string notes, Address address)
        {
            ClientName = clientName;
            CompanyName = companyName;
            PhoneNumber = phoneNumber;
            Notes = notes;
            ClientPurchaseHistory = new List<Transaction>();
            this.address = address;
        }

        public Client(string clientName, string phoneNumber, string notes, Address address)
        {
            ClientName = clientName;            
            PhoneNumber = phoneNumber;
            Notes = notes;
            ClientPurchaseHistory = new List<Transaction>();
            this.address = address;
        }
    }
}
