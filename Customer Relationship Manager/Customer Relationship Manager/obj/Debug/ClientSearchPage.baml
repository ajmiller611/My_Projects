   M S B A M L   `   `   `  ����X  TCustomer Relationship Manager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullM+clr-namespace:Customer_Relationship_ManagerCustomer_Relationship_Manager  5      4    .Customer_Relationship_Manager.ClientSearchPage   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5      R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      =local+clr-namespace:Customer_Relationship_Manager     5        N� Title$  ClientSearchPage��5	      .��5      �  � ColumnDefinitions 5   
   �� 5      �� 5       � RowDefinitions 5   
   �� 5      $
��Auto �6   �� 5      $
��Auto �6   �� 5      $
��Auto �6   �� 5      $
��Auto �6   .+�5   
   �� $��Client Search��6   $��0��6(   $��0��68   $��2��6E   $��0,0,0,0q�6Y   $	��Top=�6j   $��Center=�6�   $��22(�6�   $
��Bold"�6�   �� 5   
   $��By Client Name:��6   $��0��6*   $��1��6:   $��0,0,0,0q�6G   $	��Top=�6X   $
��Left=�6p   $��14(�6�   �� 5   
   -   5         ��Name$ SearchClientNameTextBox��5      $��0��63   $��1��6C   $��Right=�6P   $��25��6l   $��0,0,60,0q�6x    �� TextWrapping$
 Wrap=�6�   $	��Top=�6�   $	��120��6�   $��Center=�6�   �� 5   
   $��By Company Name:��6   $��1��6+   $��1��6;   $��0,0,0,0q�6H   $	��Top=�6Y   $
��Left=�6q   $��14(�6�   �� 5   
   -   5        $ SearchCompanyNameTextBox��5      $��1��64   $��1��6D   $��Right=�6Q   $��25��6m   $��0,0,30,0q�6y   $
 Wrap=�6�   $	��Top=�6�   $	��120��6�   $��Center=�6�   �� 5   
   -   5         ��Name$# ClientSearchByFirstNameButton��5      $��0��68   $��2��6H   $
��Left=�6U   $��40��6p   $��	20,10,0,0q�6|   $	��Top=�6�   $��75��6�   $
��Bold"�6�   .��5       �� $
��Wrap=�6    �� TextAlignment$ Center=�6,   Search By First Name6Y   5!      �� 5"   
   -   5        $" ClientSearchByLastNameButton��5"      $��0��67   $��2��6G   $
��Left=�6T   $��40��6o   $��
105,10,0,0q�6{   $	��Top=�6�   $��75��6�   $
��Bold"�6�   .��5#      �� $
��Wrap=�6   $ Center=�6,   Search By Last Name6X   5$      �� 5%   
   -   5        $" ClientSearchByFullNameButton��5%      $��0��67   $��2��6G   $
��Left=�6T   $��40��6o   $��
190,10,0,0q�6{   $	��Top=�6�   $��75��6�   $
��Bold"�6�   .��5&      �� $
��Wrap=�6   $ Center=�6,   Search By Full Name6X   5'      �� 5)   
   -   5        $% ClientSearchByCompanyNameButton��5)      $��1��6:   $��2��6J   $
��Left=�6W   $��40��6r   $��	20,10,0,0q�6~   $	��Top=�6�   $	��100��6�   $
��Bold"�6�   .��5*      �� $
��Wrap=�6   $ Center=�6,   Search By Company Name6[   5+      �� 5-   
   -   5        $. (ClientSearchByClientAndCompanyNameButton��5-      $��1��6C   $��2��6S   $
��Left=�6`   $��40��6{   $��130,10,30,0q�6�   $	��Top=�6�   $	��125��6�   $
��Bold"�6�   .��5.      �� $
��Wrap=�6   $ Center=�6,   #!Search By Client and Company Name6f   5/      &   System.Windows.Controls.DataGrid  51   
   -   5          Name$ ClientSearchDataGrid��51      $��0��61   $��3��6A   $��2��6N   $
��Left=�6b   $	��240��6}   $��	20,10,0,0q�6�   $	��Top=�6�   $	��540��6�      
IsReadOnly . 6�   53      54      