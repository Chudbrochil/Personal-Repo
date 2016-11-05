// Anthony Galczak
// agalczak@cnm.edu
// MyForm.h

#pragma once
#include "SimpleCalc.h"
namespace GalczakE3 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MyForm
	/// </summary>

	SimpleCalc calc;
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
	private: System::Windows::Forms::Label^  header;
	protected:

	private: System::Windows::Forms::Button^  btnPlus;
	private: System::Windows::Forms::Button^  btnMinus;
	private: System::Windows::Forms::Button^  btnMulti;
	private: System::Windows::Forms::Button^  btnDivide;
	private: System::Windows::Forms::Button^  btnClear;
	private: System::Windows::Forms::TextBox^  txtNum1;
	private: System::Windows::Forms::TextBox^  txtNum2;
	private: System::Windows::Forms::TextBox^  txtResults;
	private: System::Windows::Forms::Label^  lblOps;
	private: System::Windows::Forms::Label^  equals;








	protected:

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
			this->header = (gcnew System::Windows::Forms::Label());
			this->btnPlus = (gcnew System::Windows::Forms::Button());
			this->btnMinus = (gcnew System::Windows::Forms::Button());
			this->btnMulti = (gcnew System::Windows::Forms::Button());
			this->btnDivide = (gcnew System::Windows::Forms::Button());
			this->btnClear = (gcnew System::Windows::Forms::Button());
			this->txtNum1 = (gcnew System::Windows::Forms::TextBox());
			this->txtNum2 = (gcnew System::Windows::Forms::TextBox());
			this->txtResults = (gcnew System::Windows::Forms::TextBox());
			this->lblOps = (gcnew System::Windows::Forms::Label());
			this->equals = (gcnew System::Windows::Forms::Label());
			this->SuspendLayout();
			// 
			// header
			// 
			this->header->AutoSize = true;
			this->header->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->header->Location = System::Drawing::Point(116, 9);
			this->header->Name = L"header";
			this->header->Size = System::Drawing::Size(334, 48);
			this->header->TabIndex = 0;
			this->header->Text = L"Simple Calculator \r\nEnter two Numbers and Press a Button";
			this->header->TextAlign = System::Drawing::ContentAlignment::TopCenter;
			// 
			// btnPlus
			// 
			this->btnPlus->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnPlus->Location = System::Drawing::Point(90, 189);
			this->btnPlus->Name = L"btnPlus";
			this->btnPlus->Size = System::Drawing::Size(75, 37);
			this->btnPlus->TabIndex = 1;
			this->btnPlus->Text = L"+";
			this->btnPlus->UseVisualStyleBackColor = true;
			this->btnPlus->Click += gcnew System::EventHandler(this, &MyForm::btnPlus_Click);
			// 
			// btnMinus
			// 
			this->btnMinus->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnMinus->Location = System::Drawing::Point(218, 189);
			this->btnMinus->Name = L"btnMinus";
			this->btnMinus->Size = System::Drawing::Size(75, 37);
			this->btnMinus->TabIndex = 2;
			this->btnMinus->Text = L"-";
			this->btnMinus->UseVisualStyleBackColor = true;
			this->btnMinus->Click += gcnew System::EventHandler(this, &MyForm::btnMinus_Click);
			// 
			// btnMulti
			// 
			this->btnMulti->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnMulti->Location = System::Drawing::Point(90, 252);
			this->btnMulti->Name = L"btnMulti";
			this->btnMulti->Size = System::Drawing::Size(75, 42);
			this->btnMulti->TabIndex = 3;
			this->btnMulti->Text = L"*";
			this->btnMulti->UseVisualStyleBackColor = true;
			this->btnMulti->Click += gcnew System::EventHandler(this, &MyForm::btnMulti_Click);
			// 
			// btnDivide
			// 
			this->btnDivide->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnDivide->Location = System::Drawing::Point(218, 251);
			this->btnDivide->Name = L"btnDivide";
			this->btnDivide->Size = System::Drawing::Size(75, 43);
			this->btnDivide->TabIndex = 4;
			this->btnDivide->Text = L"/";
			this->btnDivide->UseVisualStyleBackColor = true;
			this->btnDivide->Click += gcnew System::EventHandler(this, &MyForm::btnDivide_Click);
			// 
			// btnClear
			// 
			this->btnClear->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnClear->Location = System::Drawing::Point(410, 226);
			this->btnClear->Name = L"btnClear";
			this->btnClear->Size = System::Drawing::Size(122, 68);
			this->btnClear->TabIndex = 5;
			this->btnClear->Text = L"CLEAR";
			this->btnClear->UseVisualStyleBackColor = true;
			this->btnClear->Click += gcnew System::EventHandler(this, &MyForm::btnClear_Click);
			// 
			// txtNum1
			// 
			this->txtNum1->Location = System::Drawing::Point(65, 119);
			this->txtNum1->Name = L"txtNum1";
			this->txtNum1->Size = System::Drawing::Size(100, 20);
			this->txtNum1->TabIndex = 6;
			// 
			// txtNum2
			// 
			this->txtNum2->Location = System::Drawing::Point(250, 119);
			this->txtNum2->Name = L"txtNum2";
			this->txtNum2->Size = System::Drawing::Size(100, 20);
			this->txtNum2->TabIndex = 7;
			// 
			// txtResults
			// 
			this->txtResults->Location = System::Drawing::Point(432, 119);
			this->txtResults->Name = L"txtResults";
			this->txtResults->Size = System::Drawing::Size(100, 20);
			this->txtResults->TabIndex = 8;
			// 
			// lblOps
			// 
			this->lblOps->AutoSize = true;
			this->lblOps->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblOps->Location = System::Drawing::Point(196, 119);
			this->lblOps->Name = L"lblOps";
			this->lblOps->Size = System::Drawing::Size(0, 24);
			this->lblOps->TabIndex = 9;
			// 
			// equals
			// 
			this->equals->AutoSize = true;
			this->equals->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->equals->Location = System::Drawing::Point(384, 119);
			this->equals->Name = L"equals";
			this->equals->Size = System::Drawing::Size(21, 24);
			this->equals->TabIndex = 10;
			this->equals->Text = L"=";
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->BackColor = System::Drawing::Color::CornflowerBlue;
			this->ClientSize = System::Drawing::Size(560, 357);
			this->Controls->Add(this->equals);
			this->Controls->Add(this->lblOps);
			this->Controls->Add(this->txtResults);
			this->Controls->Add(this->txtNum2);
			this->Controls->Add(this->txtNum1);
			this->Controls->Add(this->btnClear);
			this->Controls->Add(this->btnDivide);
			this->Controls->Add(this->btnMulti);
			this->Controls->Add(this->btnMinus);
			this->Controls->Add(this->btnPlus);
			this->Controls->Add(this->header);
			this->Name = L"MyForm";
			this->Text = L"MyForm";
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void MyForm_Load(System::Object^  sender, System::EventArgs^  e) {
	}
private: System::Void btnClear_Click(System::Object^  sender, System::EventArgs^  e) {
	txtNum1->Clear();
	txtNum2->Clear();
	txtResults->Clear();
	lblOps->Text = " ";
}
private: System::Void btnMinus_Click(System::Object^  sender, System::EventArgs^  e) {
	double n1 = Convert::ToDouble(txtNum1->Text);
	double n2 = Convert::ToDouble(txtNum2->Text);
	char op = '-';
	calc.SetOperation(op, n1, n2);
	txtResults->Text = calc.GetAnswer().ToString();
	lblOps->Text = "-";
}
private: System::Void btnDivide_Click(System::Object^  sender, System::EventArgs^  e) {
	double n1 = Convert::ToDouble(txtNum1->Text);
	double n2 = Convert::ToDouble(txtNum2->Text);
	char op = '/';
	lblOps->Text = "/";
	if (n2 == 0)
		txtResults->Text = "NO DIVIDE BY 0!";
	else
	{
		calc.SetOperation(op, n1, n2);
		txtResults->Text = calc.GetAnswer().ToString();
	}

}
private: System::Void btnMulti_Click(System::Object^  sender, System::EventArgs^  e) {
	double n1 = Convert::ToDouble(txtNum1->Text);
	double n2 = Convert::ToDouble(txtNum2->Text);
	char op = '*';
	calc.SetOperation(op, n1, n2);
	txtResults->Text = calc.GetAnswer().ToString();
	lblOps->Text = "*";

}
private: System::Void btnPlus_Click(System::Object^  sender, System::EventArgs^  e) {
	double n1 = Convert::ToDouble(txtNum1->Text);
	double n2 = Convert::ToDouble(txtNum2->Text);
	char op = '+';
	calc.SetOperation(op, n1, n2);
	txtResults->Text = calc.GetAnswer().ToString();
	lblOps->Text = "+";
}
};
}
