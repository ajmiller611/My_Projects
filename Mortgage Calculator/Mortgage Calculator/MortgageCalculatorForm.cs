using System;
using System.Windows.Forms;

namespace Mortgage_Calculator
{
    public partial class MortgageCalculatorForm : Form
    {
        private double principal, interestRate;
        private int term;
        private bool closeButtonSuppressMessageFlag = false;

        public MortgageCalculatorForm()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // This gives illusion of comboxBox having a default value at index 0
            termComboBox.SelectedIndex = 0;            
        }

        private void PrincipalTextBox_Leave(object sender, EventArgs e)
        {
            try
            {
                if (principalTextBox.Text.Substring(0, 1).Equals("$"))
                {
                    principal = double.Parse(principalTextBox.Text.Substring(1));
                    principalTextBox.Text = string.Format("{0:C}", principal);
                }
                else
                {
                    principal = double.Parse(principalTextBox.Text);
                    principalTextBox.Text = string.Format("{0:C}", principal);
                }
            }
            catch (ArgumentOutOfRangeException)
            {
                /* 
                 * ArgumentOutOfRangeException occurs when the Substring method is called and there is no value in the text box
                 * This empty catch block is used to suppress the error pop up when a user closes the application when the text box is empty
                 */
            }
            catch (FormatException)
            {
                // If the user is closing the program by using the closeButton then suppress incorrect format message
                if (closeButtonSuppressMessageFlag == false && principalTextBox.Text != "")
                {
                    ShowIncorrectFormatMessage(principalTextBox, "Incorrect format. Please enter a currency in the Loan Amount box");
                }                                           
            }                 
        }

        private void ShowIncorrectFormatMessage(Control currentControl, string message)
        {
            MessageBox.Show(message, "Attention", MessageBoxButtons.OK, MessageBoxIcon.Information);

            /*
             * According to the Control.Leave event documentation, atempting to set focus from within the leave event handler can cause problems.
             * Reference: https://msdn.microsoft.com/en-us/library/system.windows.forms.control.leave(v=vs.110).aspx
             * To set focus back to the principalTextBox control for user convenience, use the Control.BeginInvoke method.
             * This will make sure the Control.Leave event finishes before the Control.Focus method is called.
             */
            currentControl.BeginInvoke((MethodInvoker)delegate { currentControl.Focus(); });
        }

        private void InterestRateTextBox_Leave(object sender, EventArgs e)
        {
            try
            {
                if (interestRateTextBox.Text.Substring(0,1).Equals("%"))
                {
                    interestRate = double.Parse(interestRateTextBox.Text.Substring(1));
                    interestRateTextBox.Text = string.Format("{0:P}", interestRate / 100);
                }
                else if (interestRateTextBox.Text.Substring(interestRateTextBox.Text.Length - 1).Equals("%"))
                {
                    interestRate = double.Parse(interestRateTextBox.Text.Substring(0, interestRateTextBox.Text.Length - 1));
                    interestRateTextBox.Text = string.Format("{0:P}", interestRate / 100);
                }
                else
                {
                    interestRate = double.Parse(interestRateTextBox.Text);
                    interestRateTextBox.Text = string.Format("{0:P}", interestRate / 100);
                }
            }
            catch (ArgumentOutOfRangeException)
            {
                /* 
                * ArgumentOutOfRangeException occurs when the Substring method is called and there is no value in the text box
                * This empty catch block is used to suppress the error pop up when a user closes the application when the text box is empty
                */
            }
            catch (FormatException)
            {
                // If the user is closing the program by using the closeButton then suppress incorrect format message
                if (closeButtonSuppressMessageFlag == false && interestRateTextBox.Text != "")
                {
                    ShowIncorrectFormatMessage(interestRateTextBox, "Incorrect format. Please enter a percent in the Interest Rate box");
                }
            }
        }

        private void TermTextBox_Leave(object sender, EventArgs e)
        {
            try
            {
                term = int.Parse(termTextBox.Text);
            }
            catch (FormatException)
            {
                // If the user is closing the program by using the closeButton then suppress incorrect format message
                if (closeButtonSuppressMessageFlag == false && termTextBox.Text != "")
                {
                    ShowIncorrectFormatMessage(termTextBox, "Incorrect format. Please enter the number of years or months in the Term box");
                }
            }
        }

        private void CalculateButton_Click(object sender, EventArgs e)
        {
            // Check for values in textboxs
            if (principalTextBox.Text.Equals("") || interestRateTextBox.Text.Equals("") || termTextBox.Text.Equals(""))
            {
                ShowIncorrectFormatMessage(principalTextBox, "Please check that all data has been entered in.");                
            }
            else
            {
                // Prevent double clicking from causing problems
                calculateButton.Enabled = false;
                                
                double monthlyInterest = (interestRate / 100) / 12;

                int termInMonths = 0;
                if (termComboBox.SelectedItem.Equals("years"))
                {
                    termInMonths = term * 12;
                }
                else
                {
                    termInMonths = term;
                }

                double monthlyPayment = principal * ((monthlyInterest * Math.Pow((1 + monthlyInterest), termInMonths)) / (Math.Pow((1 + monthlyInterest), termInMonths) - 1));
                monthlyPaymentLabel.Text = string.Format("{0:C}", monthlyPayment);

                double[,] amortizationData = new double[3, termInMonths];

                // Clear the DataGridView from past data and redraw fresh DataGridView
                amortizationGridView.Rows.Clear();
                amortizationGridView.Refresh();

                // Calculate the Interest, Principal, and Balance for each month for the term of the loan
                double balance = principal;
                for (int i = 0; i < termInMonths; i++)
                {
                    amortizationData[1, i] = balance * monthlyInterest;
                    amortizationData[0, i] = monthlyPayment - amortizationData[1, i];
                    amortizationData[2, i] = balance - amortizationData[0, i];
                    balance = amortizationData[2, i];
                }

                // Gets date from computer the user is on
                DateTime today = DateTime.UtcNow;

                for (int row = 0; row < termInMonths; row++)
                {
                    DataGridViewRow newRow = new DataGridViewRow();
                    newRow.CreateCells(amortizationGridView);

                    for (int column = 0; column < 4; column++)
                    {
                        if (column == 0)
                        {
                            newRow.Cells[0].Value = today.AddMonths(row).ToString("MM/yyyy");
                            continue;
                        }
                        
                        newRow.Cells[column].Value = amortizationData[column - 1, row];
                    }

                    amortizationGridView.Rows.Add(newRow);
                }
                
                calculateButton.Enabled = true;
            }            
        }

        private void CloseButton_MouseHover(object sender, EventArgs e)
        {
            closeButtonSuppressMessageFlag = true;
        }

        private void CloseButton_MouseLeave(object sender, EventArgs e)
        {
            closeButtonSuppressMessageFlag = false;
        }        

        private void CloseButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }      
    }
}
