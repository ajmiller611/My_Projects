using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Customer_Relationship_Manager
{
    /// <summary>
    /// Interaction logic for ListClientsPage.xaml
    /// </summary>
    public partial class ListClientsPage : Page
    {
        CustomerRelationshipManagerDBDataSet crmDataSet = new CustomerRelationshipManagerDBDataSet();

        public ListClientsPage()
        {
            InitializeComponent();
        }

        private void Page_Loaded(object sender, RoutedEventArgs e)
        {
            //CustomerRelationshipManagerDBDataSet crmDataSet = new CustomerRelationshipManagerDBDataSet();

            CustomerRelationshipManagerDBDataSetTableAdapters.ClientTableAdapter clientTableAdapter = new CustomerRelationshipManagerDBDataSetTableAdapters.ClientTableAdapter();

            crmDataSet.Clear();
            clientTableAdapter.Fill(crmDataSet.Client);
            
            
            ClientDataGrid.ItemsSource = crmDataSet.Client.DefaultView;
            
        }
    }
}
