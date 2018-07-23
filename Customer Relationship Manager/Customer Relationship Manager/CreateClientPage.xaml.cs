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
    /// Interaction logic for CreateClientPage.xaml
    /// </summary>
    public partial class CreateClientPage : Page
    {
        public CreateClientPage()
        {
            InitializeComponent();
        }

        private void CreateClientButton_Click(object sender, RoutedEventArgs e)
        {
            //TODO Add validation for user entered data            

            Address newClientAddress = new Address(StreetTextBox.Text, CityTextBox.Text, StateTextBox.Text, ZipCodeTextBox.Text);

            if (CompanyNameTextBox.Text != "")
            {
                Client newClient = new Client(ClientNameTextBox.Text, CompanyNameTextBox.Text, PhoneNumberTextBox.Text, NotesTextBox.Text, newClientAddress);                
            }
            else
            {
                Client newClient = new Client(ClientNameTextBox.Text, PhoneNumberTextBox.Text, NotesTextBox.Text, newClientAddress);
            }

            MessageBox.Show("Client " + ClientNameTextBox.Text + " has been created.", "Client Created", MessageBoxButton.OK, MessageBoxImage.Information);

        }
    }
}
