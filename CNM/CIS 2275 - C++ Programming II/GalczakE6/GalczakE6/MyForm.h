#pragma once
#include "Enigma.h"
#include "UtilityFunctions.h"

//TODO:  score 43/50

namespace GalczakE6 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
	using namespace std;
	using namespace System::IO;

	/// <summary>
	/// Summary for MyForm
	/// </summary>

	Enigma enig;
	public ref class MyForm : public System::Windows::Forms::Form
	{
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
	private: System::Windows::Forms::OpenFileDialog^  openFileDialog1;
	protected:
	private: System::Windows::Forms::SaveFileDialog^  saveFileDialog1;
	private: System::Windows::Forms::Button^  btnEncode;
	private: System::Windows::Forms::Button^  btnDecode;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::Label^  label2;
	private: System::Windows::Forms::Button^  btnClear;
	private: System::Windows::Forms::Label^  label3;
	private: System::Windows::Forms::Label^  label4;
	private: System::Windows::Forms::Label^  label5;
	private: System::Windows::Forms::Label^  label6;
	private: System::Windows::Forms::TextBox^  txtMess;
	private: System::Windows::Forms::TextBox^  txtCodedMessage;
	private: System::Windows::Forms::TextBox^  txtKKey;
	private: System::Windows::Forms::TextBox^  txtMessage;

	private: System::Windows::Forms::GroupBox^  grpEncode;
	private: System::Windows::Forms::RadioButton^  rbEnigmaKey;


	private: System::Windows::Forms::NumericUpDown^  nudKey;
	private: System::Windows::Forms::RadioButton^  rbEnterKey;



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
			this->openFileDialog1 = (gcnew System::Windows::Forms::OpenFileDialog());
			this->saveFileDialog1 = (gcnew System::Windows::Forms::SaveFileDialog());
			this->btnEncode = (gcnew System::Windows::Forms::Button());
			this->btnDecode = (gcnew System::Windows::Forms::Button());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->btnClear = (gcnew System::Windows::Forms::Button());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->label5 = (gcnew System::Windows::Forms::Label());
			this->label6 = (gcnew System::Windows::Forms::Label());
			this->txtMess = (gcnew System::Windows::Forms::TextBox());
			this->txtCodedMessage = (gcnew System::Windows::Forms::TextBox());
			this->txtKKey = (gcnew System::Windows::Forms::TextBox());
			this->txtMessage = (gcnew System::Windows::Forms::TextBox());
			this->grpEncode = (gcnew System::Windows::Forms::GroupBox());
			this->rbEnigmaKey = (gcnew System::Windows::Forms::RadioButton());
			this->nudKey = (gcnew System::Windows::Forms::NumericUpDown());
			this->rbEnterKey = (gcnew System::Windows::Forms::RadioButton());
			this->grpEncode->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudKey))->BeginInit();
			this->SuspendLayout();
			// 
			// openFileDialog1
			// 
			this->openFileDialog1->FileName = L"openFileDialog1";
			// 
			// btnEncode
			// 
			this->btnEncode->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold));
			this->btnEncode->Location = System::Drawing::Point(71, 174);
			this->btnEncode->Name = L"btnEncode";
			this->btnEncode->Size = System::Drawing::Size(100, 50);
			this->btnEncode->TabIndex = 0;
			this->btnEncode->Text = L"Encode";
			this->btnEncode->UseVisualStyleBackColor = true;
			this->btnEncode->Click += gcnew System::EventHandler(this, &MyForm::btnEncode_Click);
			// 
			// btnDecode
			// 
			this->btnDecode->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold));
			this->btnDecode->Location = System::Drawing::Point(434, 110);
			this->btnDecode->Name = L"btnDecode";
			this->btnDecode->Size = System::Drawing::Size(100, 50);
			this->btnDecode->TabIndex = 1;
			this->btnDecode->Text = L"Decode";
			this->btnDecode->UseVisualStyleBackColor = true;
			this->btnDecode->Click += gcnew System::EventHandler(this, &MyForm::btnDecode_Click);
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9.5F));
			this->label1->Location = System::Drawing::Point(73, 9);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(418, 32);
			this->label1->TabIndex = 2;
			this->label1->Text = L"Welcome to the Enigma Encoding and Decoding Machine!\r\nPlease enter your secret me"
				L"ssage to be encoded or decoded below:";
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9.5F));
			this->label2->Location = System::Drawing::Point(429, 71);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(187, 32);
			this->label2->TabIndex = 3;
			this->label2->Text = L"Decode: Just press the button!\r\nThis will open your secret file.";
			// 
			// btnClear
			// 
			this->btnClear->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold));
			this->btnClear->Location = System::Drawing::Point(434, 194);
			this->btnClear->Name = L"btnClear";
			this->btnClear->Size = System::Drawing::Size(100, 50);
			this->btnClear->TabIndex = 4;
			this->btnClear->Text = L"Clear";
			this->btnClear->UseVisualStyleBackColor = true;
			this->btnClear->Click += gcnew System::EventHandler(this, &MyForm::btnClear_Click);
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9.5F));
			this->label3->Location = System::Drawing::Point(145, 248);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(167, 16);
			this->label3->TabIndex = 5;
			this->label3->Text = L"Secret Message Summary";
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->Location = System::Drawing::Point(89, 270);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(53, 13);
			this->label4->TabIndex = 6;
			this->label4->Text = L"Message:";
			// 
			// label5
			// 
			this->label5->AutoSize = true;
			this->label5->Location = System::Drawing::Point(55, 295);
			this->label5->Name = L"label5";
			this->label5->Size = System::Drawing::Size(87, 13);
			this->label5->TabIndex = 7;
			this->label5->Text = L"Coded Message:";
			// 
			// label6
			// 
			this->label6->AutoSize = true;
			this->label6->Location = System::Drawing::Point(114, 322);
			this->label6->Name = L"label6";
			this->label6->Size = System::Drawing::Size(28, 13);
			this->label6->TabIndex = 8;
			this->label6->Text = L"Key:";
			// 
			// txtMess
			// 
			this->txtMess->Location = System::Drawing::Point(148, 267);
			this->txtMess->Name = L"txtMess";
			this->txtMess->ReadOnly = true;
			this->txtMess->Size = System::Drawing::Size(386, 20);
			this->txtMess->TabIndex = 9;
			// 
			// txtCodedMessage
			// 
			this->txtCodedMessage->Location = System::Drawing::Point(148, 292);
			this->txtCodedMessage->Name = L"txtCodedMessage";
			this->txtCodedMessage->ReadOnly = true;
			this->txtCodedMessage->Size = System::Drawing::Size(386, 20);
			this->txtCodedMessage->TabIndex = 10;
			// 
			// txtKKey
			// 
			this->txtKKey->Location = System::Drawing::Point(148, 319);
			this->txtKKey->Name = L"txtKKey";
			this->txtKKey->ReadOnly = true;
			this->txtKKey->Size = System::Drawing::Size(32, 20);
			this->txtKKey->TabIndex = 11;
			// 
			// txtMessage
			// 
			this->txtMessage->Location = System::Drawing::Point(71, 148);
			this->txtMessage->Name = L"txtMessage";
			this->txtMessage->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->txtMessage->Size = System::Drawing::Size(300, 20);
			this->txtMessage->TabIndex = 12;
			// 
			// grpEncode
			// 
			this->grpEncode->Controls->Add(this->rbEnigmaKey);
			this->grpEncode->Controls->Add(this->nudKey);
			this->grpEncode->Controls->Add(this->rbEnterKey);
			this->grpEncode->Location = System::Drawing::Point(71, 51);
			this->grpEncode->Name = L"grpEncode";
			this->grpEncode->Size = System::Drawing::Size(168, 91);
			this->grpEncode->TabIndex = 13;
			this->grpEncode->TabStop = false;
			this->grpEncode->Text = L"ENCODE: Choose One:";
			// 
			// rbEnigmaKey
			// 
			this->rbEnigmaKey->AutoSize = true;
			this->rbEnigmaKey->Location = System::Drawing::Point(6, 59);
			this->rbEnigmaKey->Name = L"rbEnigmaKey";
			this->rbEnigmaKey->Size = System::Drawing::Size(156, 17);
			this->rbEnigmaKey->TabIndex = 2;
			this->rbEnigmaKey->TabStop = true;
			this->rbEnigmaKey->Text = L"Use Enigma Generated Key";
			this->rbEnigmaKey->UseVisualStyleBackColor = true;
			// 
			// nudKey
			// 
			this->nudKey->Location = System::Drawing::Point(110, 19);
			this->nudKey->Name = L"nudKey";
			this->nudKey->Size = System::Drawing::Size(52, 20);
			this->nudKey->TabIndex = 1;
			this->nudKey->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			// 
			// rbEnterKey
			// 
			this->rbEnterKey->AutoSize = true;
			this->rbEnterKey->Checked = true;
			this->rbEnterKey->Location = System::Drawing::Point(6, 19);
			this->rbEnterKey->Name = L"rbEnterKey";
			this->rbEnterKey->Size = System::Drawing::Size(80, 17);
			this->rbEnterKey->TabIndex = 0;
			this->rbEnterKey->TabStop = true;
			this->rbEnterKey->Text = L"Enter a Key";
			this->rbEnterKey->UseVisualStyleBackColor = true;
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(628, 357);
			this->Controls->Add(this->grpEncode);
			this->Controls->Add(this->txtMessage);
			this->Controls->Add(this->txtKKey);
			this->Controls->Add(this->txtCodedMessage);
			this->Controls->Add(this->txtMess);
			this->Controls->Add(this->label6);
			this->Controls->Add(this->label5);
			this->Controls->Add(this->label4);
			this->Controls->Add(this->label3);
			this->Controls->Add(this->btnClear);
			this->Controls->Add(this->label2);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->btnDecode);
			this->Controls->Add(this->btnEncode);
			this->Name = L"MyForm";
			this->Text = L"Anthony Galczak Exercise 6 Enigma Base Class";
			this->grpEncode->ResumeLayout(false);
			this->grpEncode->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudKey))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void btnDecode_Click(System::Object^  sender, System::EventArgs^  e) 
	{
		//Make two managed strings to read in the data from the file
		String^ mess;
		String^ kkey;
		//When you drag the file dialog controls onto your form, VS makes objects for you to use.  The default object name is openFileDialog1
		//initialize properties (see MSDN)
		openFileDialog1->InitialDirectory = "\\";
		openFileDialog1->Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
		openFileDialog1->FilterIndex = 2;
		openFileDialog1->RestoreDirectory = true;


		if (openFileDialog1->ShowDialog() == System::Windows::Forms::DialogResult::OK)
		{
			if ((openFileDialog1->OpenFile()) != nullptr)  //Check that file is open
			{
				//read the stream now using the Stream Reader class by making a new Stream Reader object
				StreamReader^ input = gcnew StreamReader(openFileDialog1->FileName);
				mess = input->ReadLine();
				kkey = input->ReadLine();
				input->Close();

				//now we have two managed strings and we will have to convert them to native variables
				std::string codedMess;
				To_string(mess, codedMess);
				int kk = Convert::ToInt32(kkey);

				//now set the inputs into our Enigma object
				enig.SetCodedMessage(codedMess, kk);

				//and display the results in your textboxes: message, key and filename
				txtMess->Text = gcnew String(enig.GetDecodedMessage().c_str());
				txtCodedMessage->Text = mess;
				txtKKey->Text = enig.GetKey().ToString();

			}
			else
				txtMess->Text = " File not opened!";
		}
	}

	private: System::Void btnEncode_Click(System::Object^  sender, System::EventArgs^  e) 
	{
		std::string mess;
		saveFileDialog1->InitialDirectory = "\\";
		saveFileDialog1->Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
		saveFileDialog1->FilterIndex = 2;
		saveFileDialog1->RestoreDirectory = true;
		if (saveFileDialog1->ShowDialog() == System::Windows::Forms::DialogResult::OK)
		{
			//convert managed string to native string so we can pass it into the Enigma class.
			To_string(txtMessage->Text, mess);  //get String from Message TextBox
			if (rbEnterKey->Checked)
			{
				int kk = Decimal::ToInt32(nudKey->Value);
				enig.SetMessage(mess, kk);
			}
			else
			{
				enig.SetMessage(mess);
			}
			//now get back the result
			txtMess->Text = gcnew String(enig.GetDecodedMessage().c_str());

			//TODO:  Your codedMessage isn't showing up on the form  and it has not been written in the file.  It is blank.
			txtCodedMessage->Text = gcnew String(enig.GetCodedMessage().c_str());
			txtKKey->Text = enig.GetKey().ToString();

			//and open the output stream
			StreamWriter^ output = gcnew StreamWriter(saveFileDialog1->FileName);
			output->WriteLine(gcnew String(enig.GetCodedMessage().c_str()));
			output->WriteLine(enig.GetKey().ToString());
			output->Close();
		}
		else
			txtMess->Text = "No file opened.";

	}
	private: System::Void btnClear_Click(System::Object^  sender, System::EventArgs^  e) 
	{
		// Clearing all the fields on the form
		rbEnigmaKey->Checked = false;
		rbEnterKey->Checked = true;
		nudKey->Value = 1;
		txtCodedMessage->Clear();
		txtKKey->Clear();
		txtMess->Clear();
		txtMessage->Clear();
	}
};
}
