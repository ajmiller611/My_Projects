﻿using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Customer_Relationship_Manager
{
    class CRMDB
    {
        private static readonly string connectionString = Properties.Settings.Default.connection_String;

        public CRMDB()
        {

        }        

        public static void AddNewClient(string ClientName, string CompanyName, string PhoneNumber, string Notes, Address address)
        {
            SqlConnection sqlConnection = new SqlConnection(connectionString);

            sqlConnection.Open();

            /*
             * This declartion of the SQL statement would leave this application vulnerable to SQL injection attacks
             * due to user data input being put directly into the SQL statement.
             */
            
            //string sqlStatement = "INSERT INTO Client (ClientName, CompanyName, Street, City, State, ZipCode, PhoneNumber, Notes) Values ('"
                                    //+ ClientName + "', '" + CompanyName + "', '" + address.Street + "', '" + address.City + "', '"
                                    //+ address.State + "', " + address.ZipCode + ", '" + PhoneNumber + "', '" + Notes + "');";

            
            //This SQL statement uses a paramaterized query to protect the application from SQL injection attacks by using SqlCommand parameters property

            string sqlStatement = "INSERT INTO Client (ClientName, CompanyName, Street, City, State, ZipCode, PhoneNumber, Notes) Values (" +
                                  "@ClientName, @CompanyName, @Street, @City, @State, @ZipCode, @PhoneNumber, @Notes);";

            SqlCommand command = new SqlCommand(sqlStatement, sqlConnection);
            command.Parameters.Add("@ClientName", SqlDbType.NVarChar, 50).Value = ClientName;
            command.Parameters.Add("@CompanyName", SqlDbType.NVarChar, 50).Value = CompanyName;
            command.Parameters.Add("@Street", SqlDbType.NVarChar, 50).Value = address.Street;
            command.Parameters.Add("@City", SqlDbType.NVarChar, 50).Value = address.City;
            command.Parameters.Add("@State", SqlDbType.NVarChar, 50).Value = address.State;
            command.Parameters.Add("@ZipCode", SqlDbType.Int).Value = address.ZipCode;
            command.Parameters.Add("@PhoneNumber", SqlDbType.NVarChar, 50).Value = PhoneNumber;
            command.Parameters.Add("@Notes", SqlDbType.NText).Value = Notes;            
            command.ExecuteNonQuery();

            sqlConnection.Close();
        }
    }
}