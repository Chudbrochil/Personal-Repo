#pragma once
#include "HotelRes.h"
#include "Date.h"
#include "UtilityFunctions.h"

//TODO:  score 91/100

namespace GalczakP4 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
	using namespace std;

	/// <summary>
	/// Summary for MyForm
	/// </summary>
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
	private: System::Windows::Forms::TextBox^  txbMenu;
	private: System::Windows::Forms::Label^  lblReservation;
	private: System::Windows::Forms::TextBox^  txbName;
	private: System::Windows::Forms::GroupBox^  grpArrival;
	private: System::Windows::Forms::NumericUpDown^  nudMonArr;

	private: System::Windows::Forms::NumericUpDown^  nudDayArr;
	private: System::Windows::Forms::NumericUpDown^  nudYearArr;
	private: System::Windows::Forms::Label^  lblYear;
	private: System::Windows::Forms::Label^  lblDay;
	private: System::Windows::Forms::Label^  lblMonth;
	private: System::Windows::Forms::GroupBox^  grpDeparture;
	private: System::Windows::Forms::NumericUpDown^  nudMonDep;

	private: System::Windows::Forms::NumericUpDown^  nudDayDep;
	private: System::Windows::Forms::NumericUpDown^  nudYearDep;
	private: System::Windows::Forms::Label^  lblYearDep;
	private: System::Windows::Forms::Label^  lblDayDep;
	private: System::Windows::Forms::Label^  lblMonthDep;
	private: System::Windows::Forms::Button^  btnMakeRes;
	private: System::Windows::Forms::Label^  lblParty;
	private: System::Windows::Forms::NumericUpDown^  nudParty;
	private: System::Windows::Forms::TextBox^  txbResults;

	private: System::Windows::Forms::Button^  btnClear;

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
			this->txbMenu = (gcnew System::Windows::Forms::TextBox());
			this->lblReservation = (gcnew System::Windows::Forms::Label());
			this->txbName = (gcnew System::Windows::Forms::TextBox());
			this->grpArrival = (gcnew System::Windows::Forms::GroupBox());
			this->nudMonArr = (gcnew System::Windows::Forms::NumericUpDown());
			this->nudDayArr = (gcnew System::Windows::Forms::NumericUpDown());
			this->nudYearArr = (gcnew System::Windows::Forms::NumericUpDown());
			this->lblYear = (gcnew System::Windows::Forms::Label());
			this->lblDay = (gcnew System::Windows::Forms::Label());
			this->lblMonth = (gcnew System::Windows::Forms::Label());
			this->grpDeparture = (gcnew System::Windows::Forms::GroupBox());
			this->nudMonDep = (gcnew System::Windows::Forms::NumericUpDown());
			this->nudDayDep = (gcnew System::Windows::Forms::NumericUpDown());
			this->nudYearDep = (gcnew System::Windows::Forms::NumericUpDown());
			this->lblYearDep = (gcnew System::Windows::Forms::Label());
			this->lblDayDep = (gcnew System::Windows::Forms::Label());
			this->lblMonthDep = (gcnew System::Windows::Forms::Label());
			this->btnMakeRes = (gcnew System::Windows::Forms::Button());
			this->lblParty = (gcnew System::Windows::Forms::Label());
			this->nudParty = (gcnew System::Windows::Forms::NumericUpDown());
			this->txbResults = (gcnew System::Windows::Forms::TextBox());
			this->btnClear = (gcnew System::Windows::Forms::Button());
			this->grpArrival->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudMonArr))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDayArr))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudYearArr))->BeginInit();
			this->grpDeparture->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudMonDep))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDayDep))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudYearDep))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudParty))->BeginInit();
			this->SuspendLayout();
			// 
			// txbMenu
			// 
			this->txbMenu->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->txbMenu->Location = System::Drawing::Point(36, 12);
			this->txbMenu->Multiline = true;
			this->txbMenu->Name = L"txbMenu";
			this->txbMenu->ReadOnly = true;
			this->txbMenu->Size = System::Drawing::Size(504, 74);
			this->txbMenu->TabIndex = 0;
			this->txbMenu->Text = L"Welcome to the C++ Spa and Resort!\r\nThe room rate is $185 per person per night, s"
				L"ingle or double occupancy. \r\nLodging tax is 12.33% and there is a 15% discount f"
				L"or stays longer than 5 days.";
			this->txbMenu->TextChanged += gcnew System::EventHandler(this, &MyForm::txbMenu_TextChanged);
			// 
			// lblReservation
			// 
			this->lblReservation->AutoSize = true;
			this->lblReservation->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblReservation->Location = System::Drawing::Point(35, 106);
			this->lblReservation->Name = L"lblReservation";
			this->lblReservation->Size = System::Drawing::Size(178, 19);
			this->lblReservation->TabIndex = 1;
			this->lblReservation->Text = L"Enter the Reservation Name";
			// 
			// txbName
			// 
			this->txbName->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->txbName->Location = System::Drawing::Point(36, 128);
			this->txbName->Name = L"txbName";
			this->txbName->Size = System::Drawing::Size(219, 26);
			this->txbName->TabIndex = 2;
			// 
			// grpArrival
			// 
			this->grpArrival->Controls->Add(this->nudMonArr);
			this->grpArrival->Controls->Add(this->nudDayArr);
			this->grpArrival->Controls->Add(this->nudYearArr);
			this->grpArrival->Controls->Add(this->lblYear);
			this->grpArrival->Controls->Add(this->lblDay);
			this->grpArrival->Controls->Add(this->lblMonth);
			this->grpArrival->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->grpArrival->Location = System::Drawing::Point(546, 116);
			this->grpArrival->Name = L"grpArrival";
			this->grpArrival->Size = System::Drawing::Size(200, 100);
			this->grpArrival->TabIndex = 3;
			this->grpArrival->TabStop = false;
			this->grpArrival->Text = L"Select the Date of Arrival:";
			// 
			// nudMonArr
			// 
			this->nudMonArr->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->nudMonArr->Location = System::Drawing::Point(10, 47);
			this->nudMonArr->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 12, 0, 0, 0 });
			this->nudMonArr->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			this->nudMonArr->Name = L"nudMonArr";
			this->nudMonArr->Size = System::Drawing::Size(58, 26);
			this->nudMonArr->TabIndex = 5;
			this->nudMonArr->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			// 
			// nudDayArr
			// 
			this->nudDayArr->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->nudDayArr->Location = System::Drawing::Point(74, 47);
			this->nudDayArr->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 31, 0, 0, 0 });
			this->nudDayArr->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			this->nudDayArr->Name = L"nudDayArr";
			this->nudDayArr->Size = System::Drawing::Size(53, 26);
			this->nudDayArr->TabIndex = 4;
			this->nudDayArr->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			// 
			// nudYearArr
			// 
			this->nudYearArr->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->nudYearArr->Location = System::Drawing::Point(133, 47);
			this->nudYearArr->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2016, 0, 0, 0 });
			this->nudYearArr->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2015, 0, 0, 0 });
			this->nudYearArr->Name = L"nudYearArr";
			this->nudYearArr->Size = System::Drawing::Size(61, 26);
			this->nudYearArr->TabIndex = 3;
			this->nudYearArr->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2015, 0, 0, 0 });
			// 
			// lblYear
			// 
			this->lblYear->AutoSize = true;
			this->lblYear->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblYear->Location = System::Drawing::Point(130, 20);
			this->lblYear->Name = L"lblYear";
			this->lblYear->Size = System::Drawing::Size(38, 19);
			this->lblYear->TabIndex = 2;
			this->lblYear->Text = L"Year";
			// 
			// lblDay
			// 
			this->lblDay->AutoSize = true;
			this->lblDay->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblDay->Location = System::Drawing::Point(71, 20);
			this->lblDay->Name = L"lblDay";
			this->lblDay->Size = System::Drawing::Size(35, 19);
			this->lblDay->TabIndex = 1;
			this->lblDay->Text = L"Day";
			// 
			// lblMonth
			// 
			this->lblMonth->AutoSize = true;
			this->lblMonth->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblMonth->Location = System::Drawing::Point(7, 20);
			this->lblMonth->Name = L"lblMonth";
			this->lblMonth->Size = System::Drawing::Size(49, 19);
			this->lblMonth->TabIndex = 0;
			this->lblMonth->Text = L"Month";
			// 
			// grpDeparture
			// 
			this->grpDeparture->Controls->Add(this->nudMonDep);
			this->grpDeparture->Controls->Add(this->nudDayDep);
			this->grpDeparture->Controls->Add(this->nudYearDep);
			this->grpDeparture->Controls->Add(this->lblYearDep);
			this->grpDeparture->Controls->Add(this->lblDayDep);
			this->grpDeparture->Controls->Add(this->lblMonthDep);
			this->grpDeparture->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->grpDeparture->Location = System::Drawing::Point(546, 246);
			this->grpDeparture->Name = L"grpDeparture";
			this->grpDeparture->Size = System::Drawing::Size(200, 100);
			this->grpDeparture->TabIndex = 4;
			this->grpDeparture->TabStop = false;
			this->grpDeparture->Text = L"Select the Date of Departure:";
			// 
			// nudMonDep
			// 
			this->nudMonDep->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->nudMonDep->Location = System::Drawing::Point(10, 47);
			this->nudMonDep->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 12, 0, 0, 0 });
			this->nudMonDep->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			this->nudMonDep->Name = L"nudMonDep";
			this->nudMonDep->Size = System::Drawing::Size(58, 26);
			this->nudMonDep->TabIndex = 5;
			this->nudMonDep->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			// 
			// nudDayDep
			// 
			this->nudDayDep->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->nudDayDep->Location = System::Drawing::Point(74, 47);
			this->nudDayDep->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 31, 0, 0, 0 });
			this->nudDayDep->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			this->nudDayDep->Name = L"nudDayDep";
			this->nudDayDep->Size = System::Drawing::Size(53, 26);
			this->nudDayDep->TabIndex = 4;
			this->nudDayDep->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			// 
			// nudYearDep
			// 
			this->nudYearDep->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->nudYearDep->Location = System::Drawing::Point(133, 47);
			this->nudYearDep->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2016, 0, 0, 0 });
			this->nudYearDep->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2015, 0, 0, 0 });
			this->nudYearDep->Name = L"nudYearDep";
			this->nudYearDep->Size = System::Drawing::Size(61, 26);
			this->nudYearDep->TabIndex = 3;
			this->nudYearDep->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2015, 0, 0, 0 });
			// 
			// lblYearDep
			// 
			this->lblYearDep->AutoSize = true;
			this->lblYearDep->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblYearDep->Location = System::Drawing::Point(130, 20);
			this->lblYearDep->Name = L"lblYearDep";
			this->lblYearDep->Size = System::Drawing::Size(38, 19);
			this->lblYearDep->TabIndex = 2;
			this->lblYearDep->Text = L"Year";
			// 
			// lblDayDep
			// 
			this->lblDayDep->AutoSize = true;
			this->lblDayDep->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblDayDep->Location = System::Drawing::Point(71, 20);
			this->lblDayDep->Name = L"lblDayDep";
			this->lblDayDep->Size = System::Drawing::Size(35, 19);
			this->lblDayDep->TabIndex = 1;
			this->lblDayDep->Text = L"Day";
			// 
			// lblMonthDep
			// 
			this->lblMonthDep->AutoSize = true;
			this->lblMonthDep->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblMonthDep->Location = System::Drawing::Point(7, 20);
			this->lblMonthDep->Name = L"lblMonthDep";
			this->lblMonthDep->Size = System::Drawing::Size(49, 19);
			this->lblMonthDep->TabIndex = 0;
			this->lblMonthDep->Text = L"Month";
			// 
			// btnMakeRes
			// 
			this->btnMakeRes->Font = (gcnew System::Drawing::Font(L"Stencil", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnMakeRes->Location = System::Drawing::Point(261, 106);
			this->btnMakeRes->Name = L"btnMakeRes";
			this->btnMakeRes->Size = System::Drawing::Size(119, 80);
			this->btnMakeRes->TabIndex = 5;
			this->btnMakeRes->Text = L"Make Reservation";
			this->btnMakeRes->UseVisualStyleBackColor = true;
			this->btnMakeRes->Click += gcnew System::EventHandler(this, &MyForm::btnMakeRes_Click);
			// 
			// lblParty
			// 
			this->lblParty->AutoSize = true;
			this->lblParty->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblParty->Location = System::Drawing::Point(36, 163);
			this->lblParty->Name = L"lblParty";
			this->lblParty->Size = System::Drawing::Size(163, 19);
			this->lblParty->TabIndex = 6;
			this->lblParty->Text = L"How many in your Party\?";
			// 
			// nudParty
			// 
			this->nudParty->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->nudParty->Location = System::Drawing::Point(205, 160);
			this->nudParty->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2, 0, 0, 0 });
			this->nudParty->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			this->nudParty->Name = L"nudParty";
			this->nudParty->Size = System::Drawing::Size(50, 26);
			this->nudParty->TabIndex = 7;
			this->nudParty->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			// 
			// txbResults
			// 
			this->txbResults->Font = (gcnew System::Drawing::Font(L"Poor Richard", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->txbResults->Location = System::Drawing::Point(39, 192);
			this->txbResults->Multiline = true;
			this->txbResults->Name = L"txbResults";
			this->txbResults->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->txbResults->Size = System::Drawing::Size(469, 182);
			this->txbResults->TabIndex = 8;
			// 
			// btnClear
			// 
			this->btnClear->Font = (gcnew System::Drawing::Font(L"Stencil", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnClear->Location = System::Drawing::Point(389, 106);
			this->btnClear->Name = L"btnClear";
			this->btnClear->Size = System::Drawing::Size(119, 80);
			this->btnClear->TabIndex = 9;
			this->btnClear->Text = L"Clear";
			this->btnClear->UseVisualStyleBackColor = true;
			this->btnClear->Click += gcnew System::EventHandler(this, &MyForm::btnClear_Click);
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->ClientSize = System::Drawing::Size(758, 386);
			this->Controls->Add(this->btnClear);
			this->Controls->Add(this->txbResults);
			this->Controls->Add(this->nudParty);
			this->Controls->Add(this->lblParty);
			this->Controls->Add(this->btnMakeRes);
			this->Controls->Add(this->grpDeparture);
			this->Controls->Add(this->grpArrival);
			this->Controls->Add(this->txbName);
			this->Controls->Add(this->lblReservation);
			this->Controls->Add(this->txbMenu);
			this->Name = L"MyForm";
			this->Text = L"Anthony Galczak P4 Spa";
			this->grpArrival->ResumeLayout(false);
			this->grpArrival->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudMonArr))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDayArr))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudYearArr))->EndInit();
			this->grpDeparture->ResumeLayout(false);
			this->grpDeparture->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudMonDep))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudDayDep))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudYearDep))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudParty))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
private: System::Void btnMakeRes_Click(System::Object^  sender, System::EventArgs^  e) 
{
	string name;
	To_string(txbName->Text, name);

	// Setting int for number in party from the form's nud
	int numParty = Decimal::ToInt32(nudParty->Value);

	// Setting ints for arrival nud's from the form
	int arrMon = Decimal::ToInt32(nudMonArr->Value);
	int arrDay = Decimal::ToInt32(nudDayArr->Value);
	int arrYr = Decimal::ToInt32(nudYearArr->Value);

	// Setting ints for departure nud's from the form
	int depMon = Decimal::ToInt32(nudMonDep->Value);
	int depDay = Decimal::ToInt32(nudDayDep->Value);
	int depYr = Decimal::ToInt32(nudYearDep->Value);

	// Setting arrival date into date object
	Date arr(arrMon, arrDay, arrYr, "Arrival");

	// Setting departure date into date object
	Date dep(depMon, depDay, depYr, "Departure");

	// Default date object
	Date today;

	// Instantiating object with all the data we have
	HotelRes res(name, numParty, arr, dep, today);

	// Converting a C++ string to a C# string
	txbResults->Text = gcnew String(res.GetReservationDescription().c_str());

	// Setting number of guests in the nud on the form into my class so I can display it to the user later on
	int guests = Decimal::ToInt32(nudParty->Value);
	res.SetNumberOfGuests(guests);

}
private: System::Void btnClear_Click(System::Object^  sender, System::EventArgs^  e) 
{
	// Clearing textboxes
	txbName->Clear();
	txbResults->Clear();

	// Clearing nuds
	nudDayArr->Value = 1;
	nudDayDep->Value = 1;
	nudMonArr->Value = 1;
	nudMonDep->Value = 1;
	nudYearArr->Value = 2015;
	nudYearDep->Value = 2015;
}
private: System::Void txbMenu_TextChanged(System::Object^  sender, System::EventArgs^  e) {
}
};
}

