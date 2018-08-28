using System;
using System.Collections.Generic;
using System.Data;
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
    /// Interaction logic for ClientSearchPage.xaml
    /// </summary>
    public partial class ClientSearchPage : Page
    {
        public ClientSearchPage()
        {
            InitializeComponent();
        }

        private void ClientSearchByFirstNameButton_Click(object sender, RoutedEventArgs e)
        {
            DataTable searchResultsTable = new DataTable();            
            
            if (SearchClientNameTextBox.Text != String.Empty)
            {                
                searchResultsTable = CRMDB.SearchForClient(SearchClientNameTextBox.Text, "First");
            }
            else
            {
                string message = "Please make sure the name is entered in the Client Name text box.";
                MessageBox.Show(message, "Attention", MessageBoxButton.OK, MessageBoxImage.Information);
            }

            ClientSearchDataGrid.ItemsSource = searchResultsTable.DefaultView;
        }

        private void ClientSearchByLastNameButton_Click(object sender, RoutedEventArgs e)
        {
            DataTable searchResultsTable = new DataTable();            

            if (SearchClientNameTextBox.Text != String.Empty)
            {
                searchResultsTable = CRMDB.SearchForClient(SearchClientNameTextBox.Text, "Last");
            }
            else
            {
                string message = "Please make sure the name is entered in the Client Name text box.";
                MessageBox.Show(message, "Attention", MessageBoxButton.OK, MessageBoxImage.Information);
            }

            ClientSearchDataGrid.ItemsSource = searchResultsTable.DefaultView;
        }

        private void ClientSearchByFullNameButton_Click(object sender, RoutedEventArgs e)
        {
            DataTable searchResultsTable = new DataTable();
            
            if (SearchClientNameTextBox.Text != String.Empty)
            {
                searchResultsTable = CRMDB.SearchForClient(SearchClientNameTextBox.Text, "Full");
            }
            else
            {
                string message = "Please make sure the name is entered in the Client Name text box.";
                MessageBox.Show(message, "Attention", MessageBoxButton.OK, MessageBoxImage.Information);
            }

            ClientSearchDataGrid.ItemsSource = searchResultsTable.DefaultView;
        }

        private void ClientSearchByCompanyNameButton_Click(object sender, RoutedEventArgs e)
        {
            DataTable searchResultsTable = new DataTable();

            if (SearchCompanyNameTextBox.Text != String.Empty)
            {
                searchResultsTable = CRMDB.SearchForClient(SearchClientNameTextBox.Text, SearchCompanyNameTextBox.Text, "Company");
            }
            else
            {
                string message = "Please make sure the name is entered in the Company Name text box.";
                MessageBox.Show(message, "Attention", MessageBoxButton.OK, MessageBoxImage.Information);
            }                            

            ClientSearchDataGrid.ItemsSource = searchResultsTable.DefaultView;
        }

        private void ClientSearchByClientAndCompanyNameButton_Click(object sender, RoutedEventArgs e)
        {
            DataTable searchResultsTable = new DataTable();

            if (SearchClientNameTextBox.Text != String.Empty && SearchCompanyNameTextBox.Text != String.Empty)
            {                
                searchResultsTable = CRMDB.SearchForClient(SearchClientNameTextBox.Text, SearchCompanyNameTextBox.Text, "Both");                
            }
            else
            {
                string message = "Please make sure the names are entered in the Client and Company Name text boxes.";
                MessageBox.Show(message, "Attention", MessageBoxButton.OK, MessageBoxImage.Information);
            }                            

            ClientSearchDataGrid.ItemsSource = searchResultsTable.DefaultView;
        }
    }
}
