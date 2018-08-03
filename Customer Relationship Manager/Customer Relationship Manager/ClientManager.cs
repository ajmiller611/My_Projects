using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Customer_Relationship_Manager
{
    class ClientManager
    {
        private static ClientManager clientManagerInstance;

        /*
         * Using SortedDictionary over SortedList because SortedDictonary has faster insertion and removal operations.
         * Reference: https://msdn.microsoft.com/en-us/library/ms132319.aspx
         * 
         * If the Client list became very large then SortedList could be used to reduce memory usage at a cost of speed.
         *  
         */
        SortedDictionary<string, List<Client>> clientList;

        public ClientManager()
        {
            clientList = new SortedDictionary<string, List<Client>>();
        }

        // Singleton design pattern to allow only one instance of the client manager.
        public static ClientManager getClientManagerInstance()
        {
            if (clientManagerInstance == null)
            {
                clientManagerInstance = new ClientManager();
            }

            return clientManagerInstance;
        }

        public void AddClientToClientList(Client client)
        {
            // Grab starting letter from client's name and make it upper case to test against the keys in clientList
            string startingLetter = client.ClientName.Substring(0, 1).ToUpper();

            bool keyFoundFlag = false;

            // Find startingLetter that matches the key in the clientList and add new client.
            foreach (KeyValuePair<string, List<Client>> item in clientList)
            {
                if (startingLetter.Equals(item.Key))
                {
                    item.Value.Add(client);
                    keyFoundFlag = true;
                }                
            }

            // If startingLetter doesn't match any keys then create new key for clientList and add client to the list paired with that key.
            if (keyFoundFlag == false)
            {
                List<Client> list = new List<Client>();
                clientList.Add(startingLetter, list);
                clientList[startingLetter].Add(client);
            }
        }
    }
}
