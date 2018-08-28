using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Customer_Relationship_Manager
{
    /// <summary>
    /// Interaction logic for HomePage.xaml
    /// </summary>
    public partial class HomePage : Page
    {
        public HomePage()
        {
            InitializeComponent();
        }

        private void CreateClient_Click(object sender, RoutedEventArgs e)
        {
            CreateClientPage createClientPage = new CreateClientPage();
            this.NavigationService.Navigate(createClientPage);
        }

        private void ListClients_Click(object sender, RoutedEventArgs e)
        {
            ListClientsPage listClientsPage = new ListClientsPage();
            this.NavigationService.Navigate(listClientsPage);
        }

        private void ClientPurchaseHistory_Click(object sender, RoutedEventArgs e)
        {

        }

        private void ClientSearch_Click(object sender, RoutedEventArgs e)
        {
            ClientSearchPage clientSearchPage = new ClientSearchPage();
            this.NavigationService.Navigate(clientSearchPage);
        }
    }
}
