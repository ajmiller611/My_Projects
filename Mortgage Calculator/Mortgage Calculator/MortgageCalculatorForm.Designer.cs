namespace Mortgage_Calculator
{
    partial class MortgageCalculatorForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle25 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle26 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle27 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle28 = new System.Windows.Forms.DataGridViewCellStyle();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.titleLabel = new System.Windows.Forms.Label();
            this.principalLabel = new System.Windows.Forms.Label();
            this.interestRateLabel = new System.Windows.Forms.Label();
            this.interestRateTextBox = new System.Windows.Forms.TextBox();
            this.termLabel = new System.Windows.Forms.Label();
            this.termTextBox = new System.Windows.Forms.TextBox();
            this.principalTextBox = new System.Windows.Forms.TextBox();
            this.termComboBox = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.paymentLabel = new System.Windows.Forms.Label();
            this.flowLayoutPanel1 = new System.Windows.Forms.FlowLayoutPanel();
            this.closeButton = new System.Windows.Forms.Button();
            this.calculateButton = new System.Windows.Forms.Button();
            this.monthlyPaymentLabel = new System.Windows.Forms.Label();
            this.amortizationGridView = new System.Windows.Forms.DataGridView();
            this.label2 = new System.Windows.Forms.Label();
            this.Column1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column3 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column4 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tableLayoutPanel1.SuspendLayout();
            this.flowLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.amortizationGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 3;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 45F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 55F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 430F));
            this.tableLayoutPanel1.Controls.Add(this.flowLayoutPanel1, 0, 6);
            this.tableLayoutPanel1.Controls.Add(this.titleLabel, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.principalLabel, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.interestRateLabel, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.interestRateTextBox, 1, 2);
            this.tableLayoutPanel1.Controls.Add(this.principalTextBox, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.termComboBox, 1, 4);
            this.tableLayoutPanel1.Controls.Add(this.termTextBox, 1, 3);
            this.tableLayoutPanel1.Controls.Add(this.label1, 0, 4);
            this.tableLayoutPanel1.Controls.Add(this.termLabel, 0, 3);
            this.tableLayoutPanel1.Controls.Add(this.paymentLabel, 0, 5);
            this.tableLayoutPanel1.Controls.Add(this.monthlyPaymentLabel, 1, 5);
            this.tableLayoutPanel1.Controls.Add(this.amortizationGridView, 2, 1);
            this.tableLayoutPanel1.Controls.Add(this.label2, 2, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 6;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 9.000802F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.99994F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.99994F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.99994F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 15.99962F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.99995F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 8.999788F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(909, 475);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // titleLabel
            // 
            this.titleLabel.AutoSize = true;
            this.tableLayoutPanel1.SetColumnSpan(this.titleLabel, 2);
            this.titleLabel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.titleLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.titleLabel.Location = new System.Drawing.Point(3, 0);
            this.titleLabel.Name = "titleLabel";
            this.titleLabel.Size = new System.Drawing.Size(472, 42);
            this.titleLabel.TabIndex = 0;
            this.titleLabel.Text = "Mortgage Calculator";
            this.titleLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // principalLabel
            // 
            this.principalLabel.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.principalLabel.AutoSize = true;
            this.principalLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.principalLabel.Location = new System.Drawing.Point(84, 70);
            this.principalLabel.Name = "principalLabel";
            this.principalLabel.Size = new System.Drawing.Size(128, 24);
            this.principalLabel.TabIndex = 1;
            this.principalLabel.Text = "Loan Amount:";
            // 
            // interestRateLabel
            // 
            this.interestRateLabel.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.interestRateLabel.AutoSize = true;
            this.interestRateLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.interestRateLabel.Location = new System.Drawing.Point(94, 150);
            this.interestRateLabel.Name = "interestRateLabel";
            this.interestRateLabel.Size = new System.Drawing.Size(118, 24);
            this.interestRateLabel.TabIndex = 3;
            this.interestRateLabel.Text = "Interest Rate:";
            this.interestRateLabel.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // interestRateTextBox
            // 
            this.interestRateTextBox.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.interestRateTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.interestRateTextBox.Location = new System.Drawing.Point(218, 147);
            this.interestRateTextBox.Name = "interestRateTextBox";
            this.interestRateTextBox.Size = new System.Drawing.Size(70, 29);
            this.interestRateTextBox.TabIndex = 4;
            this.interestRateTextBox.Text = "0.00%";
            this.interestRateTextBox.Leave += new System.EventHandler(this.InterestRateTextBox_Leave);
            // 
            // termLabel
            // 
            this.termLabel.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.termLabel.AutoSize = true;
            this.termLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.termLabel.Location = new System.Drawing.Point(152, 230);
            this.termLabel.Name = "termLabel";
            this.termLabel.Size = new System.Drawing.Size(60, 24);
            this.termLabel.TabIndex = 5;
            this.termLabel.Text = "Term:";
            // 
            // termTextBox
            // 
            this.termTextBox.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.termTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.termTextBox.Location = new System.Drawing.Point(218, 227);
            this.termTextBox.Name = "termTextBox";
            this.termTextBox.Size = new System.Drawing.Size(70, 29);
            this.termTextBox.TabIndex = 6;
            this.termTextBox.Leave += new System.EventHandler(this.TermTextBox_Leave);
            // 
            // principalTextBox
            // 
            this.principalTextBox.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.principalTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.principalTextBox.Location = new System.Drawing.Point(218, 67);
            this.principalTextBox.Name = "principalTextBox";
            this.principalTextBox.Size = new System.Drawing.Size(150, 29);
            this.principalTextBox.TabIndex = 2;
            this.principalTextBox.Text = "$0.00";
            this.principalTextBox.Leave += new System.EventHandler(this.PrincipalTextBox_Leave);
            // 
            // termComboBox
            // 
            this.termComboBox.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.termComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.termComboBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.termComboBox.FormattingEnabled = true;
            this.termComboBox.Items.AddRange(new object[] {
            "years",
            "months"});
            this.termComboBox.Location = new System.Drawing.Point(218, 309);
            this.termComboBox.Name = "termComboBox";
            this.termComboBox.Size = new System.Drawing.Size(121, 32);
            this.termComboBox.TabIndex = 7;
            // 
            // label1
            // 
            this.label1.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(104, 307);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(108, 24);
            this.label1.TabIndex = 9;
            this.label1.Text = "Term Type:";
            // 
            // paymentLabel
            // 
            this.paymentLabel.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.paymentLabel.AutoSize = true;
            this.paymentLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.paymentLabel.Location = new System.Drawing.Point(53, 380);
            this.paymentLabel.Name = "paymentLabel";
            this.paymentLabel.Size = new System.Drawing.Size(159, 24);
            this.paymentLabel.TabIndex = 10;
            this.paymentLabel.Text = "Monthly Payment:";
            // 
            // flowLayoutPanel1
            // 
            this.tableLayoutPanel1.SetColumnSpan(this.flowLayoutPanel1, 2);
            this.flowLayoutPanel1.Controls.Add(this.closeButton);
            this.flowLayoutPanel1.Controls.Add(this.calculateButton);
            this.flowLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flowLayoutPanel1.FlowDirection = System.Windows.Forms.FlowDirection.RightToLeft;
            this.flowLayoutPanel1.Location = new System.Drawing.Point(3, 431);
            this.flowLayoutPanel1.Name = "flowLayoutPanel1";
            this.flowLayoutPanel1.Size = new System.Drawing.Size(472, 41);
            this.flowLayoutPanel1.TabIndex = 11;
            // 
            // closeButton
            // 
            this.closeButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 13F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.closeButton.Location = new System.Drawing.Point(369, 3);
            this.closeButton.Name = "closeButton";
            this.closeButton.Size = new System.Drawing.Size(100, 30);
            this.closeButton.TabIndex = 0;
            this.closeButton.Text = "Close";
            this.closeButton.UseVisualStyleBackColor = true;
            this.closeButton.Click += new System.EventHandler(this.CloseButton_Click);
            this.closeButton.MouseLeave += new System.EventHandler(this.CloseButton_MouseLeave);
            this.closeButton.MouseHover += new System.EventHandler(this.CloseButton_MouseHover);
            // 
            // calculateButton
            // 
            this.calculateButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 13F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.calculateButton.Location = new System.Drawing.Point(263, 3);
            this.calculateButton.Name = "calculateButton";
            this.calculateButton.Size = new System.Drawing.Size(100, 30);
            this.calculateButton.TabIndex = 1;
            this.calculateButton.Text = "Calculate";
            this.calculateButton.UseVisualStyleBackColor = true;
            this.calculateButton.Click += new System.EventHandler(this.CalculateButton_Click);
            // 
            // monthlyPaymentLabel
            // 
            this.monthlyPaymentLabel.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.monthlyPaymentLabel.AutoSize = true;
            this.monthlyPaymentLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.monthlyPaymentLabel.Location = new System.Drawing.Point(218, 380);
            this.monthlyPaymentLabel.Name = "monthlyPaymentLabel";
            this.monthlyPaymentLabel.Size = new System.Drawing.Size(55, 24);
            this.monthlyPaymentLabel.TabIndex = 12;
            this.monthlyPaymentLabel.Text = "$0.00";
            // 
            // amortizationGridView
            // 
            this.amortizationGridView.AllowUserToAddRows = false;
            this.amortizationGridView.AllowUserToDeleteRows = false;
            this.amortizationGridView.AllowUserToResizeColumns = false;
            this.amortizationGridView.AllowUserToResizeRows = false;
            this.amortizationGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.amortizationGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Column1,
            this.Column2,
            this.Column3,
            this.Column4});
            this.amortizationGridView.Location = new System.Drawing.Point(481, 45);
            this.amortizationGridView.Name = "amortizationGridView";
            this.amortizationGridView.ReadOnly = true;
            this.amortizationGridView.RowHeadersVisible = false;
            this.tableLayoutPanel1.SetRowSpan(this.amortizationGridView, 6);
            this.amortizationGridView.Size = new System.Drawing.Size(405, 383);
            this.amortizationGridView.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(481, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(425, 42);
            this.label2.TabIndex = 13;
            this.label2.Text = "Amortization Schedule";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Column1
            // 
            dataGridViewCellStyle25.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            dataGridViewCellStyle25.Format = "C2";
            dataGridViewCellStyle25.NullValue = null;
            this.Column1.DefaultCellStyle = dataGridViewCellStyle25;
            this.Column1.HeaderText = "Date";
            this.Column1.Name = "Column1";
            this.Column1.ReadOnly = true;
            this.Column1.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.Column1.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            // 
            // Column2
            // 
            dataGridViewCellStyle26.Format = "C2";
            dataGridViewCellStyle26.NullValue = null;
            this.Column2.DefaultCellStyle = dataGridViewCellStyle26;
            this.Column2.HeaderText = "Principal";
            this.Column2.Name = "Column2";
            this.Column2.ReadOnly = true;
            this.Column2.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.Column2.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            // 
            // Column3
            // 
            dataGridViewCellStyle27.Format = "C2";
            dataGridViewCellStyle27.NullValue = null;
            this.Column3.DefaultCellStyle = dataGridViewCellStyle27;
            this.Column3.HeaderText = "Interest";
            this.Column3.Name = "Column3";
            this.Column3.ReadOnly = true;
            this.Column3.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.Column3.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            // 
            // Column4
            // 
            dataGridViewCellStyle28.Format = "C2";
            dataGridViewCellStyle28.NullValue = null;
            this.Column4.DefaultCellStyle = dataGridViewCellStyle28;
            this.Column4.HeaderText = "Balance";
            this.Column4.Name = "Column4";
            this.Column4.ReadOnly = true;
            this.Column4.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.Column4.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(909, 475);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.flowLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.amortizationGridView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label titleLabel;
        private System.Windows.Forms.Label principalLabel;
        private System.Windows.Forms.Label interestRateLabel;
        private System.Windows.Forms.TextBox interestRateTextBox;
        private System.Windows.Forms.Label termLabel;
        private System.Windows.Forms.TextBox termTextBox;
        private System.Windows.Forms.TextBox principalTextBox;
        private System.Windows.Forms.ComboBox termComboBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanel1;
        private System.Windows.Forms.Button closeButton;
        private System.Windows.Forms.Button calculateButton;
        private System.Windows.Forms.Label paymentLabel;
        private System.Windows.Forms.Label monthlyPaymentLabel;
        private System.Windows.Forms.DataGridView amortizationGridView;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column1;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column2;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column3;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column4;
    }
}

