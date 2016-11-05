#pragma once
#include "VendingMachine.h"//added to give access to vending machine
namespace GalczakGreshamP5 {
	
	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	VendingMachine shop; //created the object for vending machine
	
	/// <summary>
	/// Summary for MyForm
	/// </summary>
	
	public ref class MyForm : public System::Windows::Forms::Form
	{

		int select = 0;
		
	public:
		MyForm(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
			
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MyForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::Label^  lblHeader;
	private: System::Windows::Forms::Label^  lblDollar;
	private: System::Windows::Forms::Label^  lblQuarter;
	private: System::Windows::Forms::Label^  lblDime;
	private: System::Windows::Forms::Label^  lblNickel;
	protected:

	protected:




	private: System::Windows::Forms::NumericUpDown^  nudDollar;
	private: System::Windows::Forms::NumericUpDown^  nudQuarter;
	private: System::Windows::Forms::NumericUpDown^  nudDime;
	private: System::Windows::Forms::NumericUpDown^  nudNickel;





	private: System::Windows::Forms::GroupBox^  grpChars;
	private: System::Windows::Forms::RadioButton^  radChar5;


	private: System::Windows::Forms::RadioButton^  radChar4;

	private: System::Windows::Forms::RadioButton^  radChar3;

	private: System::Windows::Forms::RadioButton^  radChar2;

	private: System::Windows::Forms::RadioButton^  radChar1;
	private: System::Windows::Forms::Button^  btnBuy;
	private: System::Windows::Forms::Button^  btnClear;
	private: System::Windows::Forms::Button^  btnExit;
	private: System::Windows::Forms::TextBox^  txbResults;
	private: System::Windows::Forms::PictureBox^  picBox;







	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			System::ComponentModel::ComponentResourceManager^  resources = (gcnew System::ComponentModel::ComponentResourceManager(MyForm::typeid));
			this->lblHeader = (gcnew System::Windows::Forms::Label());
			this->lblDollar = (gcnew System::Windows::Forms::Label());
			this->lblQuarter = (gcnew System::Windows::Forms::Label());
			this->lblDime = (gcnew System::Windows::Forms::Label());
			this->lblNickel = (gcnew System::Windows::Forms::Label());
			this->nudDollar = (gcnew System::Windows::Forms::NumericUpDown());
			this->nudQuarter = (gcnew System::Windows::Forms::NumericUpDown());
			this->nudDime = (gcnew System::Windows::Forms::NumericUpDown());
			this->nudNickel = (gcnew System::Windows::Forms::NumericUpDown());
			this->grpChars = (gcnew System::Windows::Forms::GroupBox());
			this->radChar5 = (gcnew System::Windows::Forms::RadioButton());
			this->radChar4 = (gcnew System::Windows::Forms::RadioButton());
			this->radChar3 = (gcnew System::Windows::Forms::RadioButton());
			this->radChar2 = (gcnew System::Windows::Forms::RadioButton());
			this->radChar1 = (gcnew System::Windows::Forms::RadioButton());
			this->btnBuy = (gcnew System::Windows::Forms::Button());
			this->btnClear = (gcnew System::Windows::Forms::Button());
			this->btnExit = (gcnew System::Windows::Forms::Button());
			this->txbResults = (gcnew System::Windows::Forms::TextBox());
			this->picBox = (gcnew System::Windows::Forms::PictureBox());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDollar))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudQuarter))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDime))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudNickel))->BeginInit();
			this->grpChars->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->picBox))->BeginInit();
			this->SuspendLayout();
			// 
			// lblHeader
			// 
			this->lblHeader->AutoSize = true;
			this->lblHeader->Location = System::Drawing::Point(13, 13);
			this->lblHeader->Name = L"lblHeader";
			this->lblHeader->Size = System::Drawing::Size(240, 26);
			this->lblHeader->TabIndex = 0;
			this->lblHeader->Text = L"League of Legends Character Purchase Machine\r\nEnter your money and then select a "
				L"Character";
			// 
			// lblDollar
			// 
			this->lblDollar->AutoSize = true;
			this->lblDollar->Location = System::Drawing::Point(16, 61);
			this->lblDollar->Name = L"lblDollar";
			this->lblDollar->Size = System::Drawing::Size(55, 13);
			this->lblDollar->TabIndex = 1;
			this->lblDollar->Text = L"Dollar Bills";
			// 
			// lblQuarter
			// 
			this->lblQuarter->AutoSize = true;
			this->lblQuarter->Location = System::Drawing::Point(120, 61);
			this->lblQuarter->Name = L"lblQuarter";
			this->lblQuarter->Size = System::Drawing::Size(47, 13);
			this->lblQuarter->TabIndex = 2;
			this->lblQuarter->Text = L"Quarters";
			// 
			// lblDime
			// 
			this->lblDime->AutoSize = true;
			this->lblDime->Location = System::Drawing::Point(225, 61);
			this->lblDime->Name = L"lblDime";
			this->lblDime->Size = System::Drawing::Size(36, 13);
			this->lblDime->TabIndex = 3;
			this->lblDime->Text = L"Dimes";
			// 
			// lblNickel
			// 
			this->lblNickel->AutoSize = true;
			this->lblNickel->Location = System::Drawing::Point(341, 60);
			this->lblNickel->Name = L"lblNickel";
			this->lblNickel->Size = System::Drawing::Size(42, 13);
			this->lblNickel->TabIndex = 4;
			this->lblNickel->Text = L"Nickels";
			// 
			// nudDollar
			// 
			this->nudDollar->Location = System::Drawing::Point(16, 78);
			this->nudDollar->Name = L"nudDollar";
			this->nudDollar->Size = System::Drawing::Size(72, 20);
			this->nudDollar->TabIndex = 5;
			// 
			// nudQuarter
			// 
			this->nudQuarter->Location = System::Drawing::Point(123, 78);
			this->nudQuarter->Name = L"nudQuarter";
			this->nudQuarter->Size = System::Drawing::Size(72, 20);
			this->nudQuarter->TabIndex = 6;
			// 
			// nudDime
			// 
			this->nudDime->Location = System::Drawing::Point(228, 78);
			this->nudDime->Name = L"nudDime";
			this->nudDime->Size = System::Drawing::Size(72, 20);
			this->nudDime->TabIndex = 7;
			// 
			// nudNickel
			// 
			this->nudNickel->Location = System::Drawing::Point(344, 78);
			this->nudNickel->Name = L"nudNickel";
			this->nudNickel->Size = System::Drawing::Size(72, 20);
			this->nudNickel->TabIndex = 8;
			// 
			// grpChars
			// 
			this->grpChars->Controls->Add(this->radChar5);
			this->grpChars->Controls->Add(this->radChar4);
			this->grpChars->Controls->Add(this->radChar3);
			this->grpChars->Controls->Add(this->radChar2);
			this->grpChars->Controls->Add(this->radChar1);
			this->grpChars->Location = System::Drawing::Point(19, 104);
			this->grpChars->Name = L"grpChars";
			this->grpChars->Size = System::Drawing::Size(195, 145);
			this->grpChars->TabIndex = 10;
			this->grpChars->TabStop = false;
			this->grpChars->Text = L"Select a Character!";
			// 
			// radChar5
			// 
			this->radChar5->AutoSize = true;
			this->radChar5->Location = System::Drawing::Point(7, 116);
			this->radChar5->Name = L"radChar5";
			this->radChar5->Size = System::Drawing::Size(81, 17);
			this->radChar5->TabIndex = 4;
			this->radChar5->TabStop = true;
			this->radChar5->Text = L"Sivir - $1.95";
			this->radChar5->UseVisualStyleBackColor = true;
			this->radChar5->CheckedChanged += gcnew System::EventHandler(this, &MyForm::radChar5_CheckedChanged);
			// 
			// radChar4
			// 
			this->radChar4->AutoSize = true;
			this->radChar4->Location = System::Drawing::Point(7, 92);
			this->radChar4->Name = L"radChar4";
			this->radChar4->Size = System::Drawing::Size(79, 17);
			this->radChar4->TabIndex = 3;
			this->radChar4->TabStop = true;
			this->radChar4->Text = L"Jinx - $3.95";
			this->radChar4->UseVisualStyleBackColor = true;
			this->radChar4->CheckedChanged += gcnew System::EventHandler(this, &MyForm::radChar4_CheckedChanged);
			// 
			// radChar3
			// 
			this->radChar3->AutoSize = true;
			this->radChar3->Location = System::Drawing::Point(7, 68);
			this->radChar3->Name = L"radChar3";
			this->radChar3->Size = System::Drawing::Size(94, 17);
			this->radChar3->TabIndex = 2;
			this->radChar3->TabStop = true;
			this->radChar3->Text = L"Thresh - $3.95";
			this->radChar3->UseVisualStyleBackColor = true;
			this->radChar3->CheckedChanged += gcnew System::EventHandler(this, &MyForm::radChar3_CheckedChanged);
			// 
			// radChar2
			// 
			this->radChar2->AutoSize = true;
			this->radChar2->Location = System::Drawing::Point(7, 44);
			this->radChar2->Name = L"radChar2";
			this->radChar2->Size = System::Drawing::Size(79, 17);
			this->radChar2->TabIndex = 1;
			this->radChar2->TabStop = true;
			this->radChar2->Text = L"Fizz - $1.95";
			this->radChar2->UseVisualStyleBackColor = true;
			this->radChar2->CheckedChanged += gcnew System::EventHandler(this, &MyForm::radChar2_CheckedChanged);
			// 
			// radChar1
			// 
			this->radChar1->AutoSize = true;
			this->radChar1->Location = System::Drawing::Point(7, 20);
			this->radChar1->Name = L"radChar1";
			this->radChar1->Size = System::Drawing::Size(88, 17);
			this->radChar1->TabIndex = 0;
			this->radChar1->TabStop = true;
			this->radChar1->Text = L"Annie - $1.95";
			this->radChar1->UseVisualStyleBackColor = true;
			this->radChar1->CheckedChanged += gcnew System::EventHandler(this, &MyForm::radChar1_CheckedChanged);
			// 
			// btnBuy
			// 
			this->btnBuy->Location = System::Drawing::Point(19, 255);
			this->btnBuy->Name = L"btnBuy";
			this->btnBuy->Size = System::Drawing::Size(92, 58);
			this->btnBuy->TabIndex = 11;
			this->btnBuy->Text = L"BUY";
			this->btnBuy->UseVisualStyleBackColor = true;
			this->btnBuy->Click += gcnew System::EventHandler(this, &MyForm::btnBuy_Click);
			// 
			// btnClear
			// 
			this->btnClear->Location = System::Drawing::Point(208, 255);
			this->btnClear->Name = L"btnClear";
			this->btnClear->Size = System::Drawing::Size(92, 58);
			this->btnClear->TabIndex = 12;
			this->btnClear->Text = L"CLEAR";
			this->btnClear->UseVisualStyleBackColor = true;
			this->btnClear->Click += gcnew System::EventHandler(this, &MyForm::btnClear_Click);
			// 
			// btnExit
			// 
			this->btnExit->Location = System::Drawing::Point(373, 255);
			this->btnExit->Name = L"btnExit";
			this->btnExit->Size = System::Drawing::Size(92, 58);
			this->btnExit->TabIndex = 13;
			this->btnExit->Text = L"EXIT";
			this->btnExit->UseVisualStyleBackColor = true;
			this->btnExit->Click += gcnew System::EventHandler(this, &MyForm::btnExit_Click);
			// 
			// txbResults
			// 
			this->txbResults->Location = System::Drawing::Point(19, 319);
			this->txbResults->Multiline = true;
			this->txbResults->Name = L"txbResults";
			this->txbResults->ReadOnly = true;
			this->txbResults->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->txbResults->Size = System::Drawing::Size(446, 155);
			this->txbResults->TabIndex = 14;
			// 
			// picBox
			// 
			this->picBox->Image = (cli::safe_cast<System::Drawing::Image^>(resources->GetObject(L"picBox.Image")));
			this->picBox->Location = System::Drawing::Point(242, 104);
			this->picBox->Name = L"picBox";
			this->picBox->Size = System::Drawing::Size(223, 145);
			this->picBox->SizeMode = System::Windows::Forms::PictureBoxSizeMode::StretchImage;
			this->picBox->TabIndex = 15;
			this->picBox->TabStop = false;
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(499, 486);
			this->Controls->Add(this->picBox);
			this->Controls->Add(this->txbResults);
			this->Controls->Add(this->btnExit);
			this->Controls->Add(this->btnClear);
			this->Controls->Add(this->btnBuy);
			this->Controls->Add(this->grpChars);
			this->Controls->Add(this->nudNickel);
			this->Controls->Add(this->nudDime);
			this->Controls->Add(this->nudQuarter);
			this->Controls->Add(this->nudDollar);
			this->Controls->Add(this->lblNickel);
			this->Controls->Add(this->lblDime);
			this->Controls->Add(this->lblQuarter);
			this->Controls->Add(this->lblDollar);
			this->Controls->Add(this->lblHeader);
			this->Name = L"MyForm";
			this->Text = L"Program 5 - GalczakGresham - Character Purchaser";
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDollar))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudQuarter))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDime))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudNickel))->EndInit();
			this->grpChars->ResumeLayout(false);
			this->grpChars->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->picBox))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion


// Buying your character
private: System::Void btnBuy_Click(System::Object^  sender, System::EventArgs^  e) 
{
	// Setting nuds values into variables
	int doll, quar, dime, nick;

	doll = Decimal::ToInt32(nudDollar->Value);
	quar = Decimal::ToInt32(nudQuarter->Value);
	dime = Decimal::ToInt32(nudDime->Value);
	nick = Decimal::ToInt32(nudNickel->Value);

	// Setting these variables into the vending machine including the select array item
	shop.SetInput(doll, quar, dime, nick, select);

	// Money left in bank
	txbResults->Text = gcnew String(shop.GetDispenseString().c_str());

}

// Clearing the form
private: System::Void btnClear_Click(System::Object^  sender, System::EventArgs^  e) 
{
	// Clearing all the fields
	txbResults->Clear();
	nudDollar->Value = 0;
	nudQuarter->Value = 0;
	nudDime->Value = 0;
	nudNickel->Value = 0;

	// Clearing the radio buttons
	radChar1->Checked = false;
	radChar2->Checked = false;
	radChar3->Checked = false;
	radChar4->Checked = false;
	radChar5->Checked = false;

}

// Exiting GUI
private: System::Void btnExit_Click(System::Object^  sender, System::EventArgs^  e) 
{
	// Closing the window
	Application::Exit();
	shop.~VendingMachine();
}

// Selecting Annie
private: System::Void radChar1_CheckedChanged(System::Object^  sender, System::EventArgs^  e) 
{
	// Picture change
	if (radChar1->Checked)
	{
		picBox->Load("Annie.jpg");
		select = 0;
	}

}

// Selecting Fizz
private: System::Void radChar2_CheckedChanged(System::Object^  sender, System::EventArgs^  e) 
{
	// Picture change
	if (radChar2->Checked)
	{
		picBox->Load("Fizz.jpg");
		select = 1;
	}
}

// Selecting Thresh
private: System::Void radChar3_CheckedChanged(System::Object^  sender, System::EventArgs^  e) 
{
	// Picture change
	if (radChar3->Checked)
	{
		picBox->Load("Thresh.jpg");
		select = 2;
	}
}

// Selecting Jinx
private: System::Void radChar4_CheckedChanged(System::Object^  sender, System::EventArgs^  e) 
{
	// Picture change
	if (radChar4->Checked)
	{
		picBox->Load("Jinx.jpg");
		select = 3;
	}
}

// Selecting Sivir
private: System::Void radChar5_CheckedChanged(System::Object^  sender, System::EventArgs^  e) 
{
	// Picture change
	if (radChar5->Checked)
	{
		picBox->Load("Sivir.jpg");
		select = 4;
	}
}

private: System::Void picBox_Click(System::Object^  sender, System::EventArgs^  e) {
}
};
}
