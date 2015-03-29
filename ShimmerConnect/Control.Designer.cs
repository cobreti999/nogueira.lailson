/*
 * Copyright (c) 2010, Shimmer Research, Ltd.
 * All rights reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:

 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *     * Neither the name of Shimmer Research, Ltd. nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Mike Healy
 * @date   January, 2011
 */


namespace ShimmerConnect
{
    partial class Control
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
            this.components = new System.ComponentModel.Container();
            this.btnStop = new System.Windows.Forms.Button();
            this.btnStart = new System.Windows.Forms.Button();
            this.btnDisconnect = new System.Windows.Forms.Button();
            this.btnConnect = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.cmbComPortSelect = new System.Windows.Forms.ComboBox();
            this.serialPort1 = new System.IO.Ports.SerialPort(this.components);
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.patientLastNameTextbox = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.patientFirstNameTextbox = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.patientLabel = new System.Windows.Forms.Label();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.userTypeTextbox = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.userLastNameTextbox = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.userFirstNameTextbox = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.deviceLocationTextbox = new System.Windows.Forms.TextBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnStop
            // 
            this.btnStop.Location = new System.Drawing.Point(163, 51);
            this.btnStop.Name = "btnStop";
            this.btnStop.Size = new System.Drawing.Size(137, 23);
            this.btnStop.TabIndex = 28;
            this.btnStop.Text = "Stop Streaming";
            this.btnStop.UseVisualStyleBackColor = true;
            this.btnStop.Click += new System.EventHandler(this.btnStop_Click);
            // 
            // btnStart
            // 
            this.btnStart.Location = new System.Drawing.Point(8, 51);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(137, 23);
            this.btnStart.TabIndex = 27;
            this.btnStart.Text = "Start Streaming";
            this.btnStart.UseVisualStyleBackColor = true;
            this.btnStart.Click += new System.EventHandler(this.btnStart_Click);
            // 
            // btnDisconnect
            // 
            this.btnDisconnect.Location = new System.Drawing.Point(215, 22);
            this.btnDisconnect.Name = "btnDisconnect";
            this.btnDisconnect.Size = new System.Drawing.Size(75, 23);
            this.btnDisconnect.TabIndex = 26;
            this.btnDisconnect.Text = "Disconnect";
            this.btnDisconnect.UseVisualStyleBackColor = true;
            this.btnDisconnect.Click += new System.EventHandler(this.btnDisconnect_Click);
            // 
            // btnConnect
            // 
            this.btnConnect.Location = new System.Drawing.Point(6, 22);
            this.btnConnect.Name = "btnConnect";
            this.btnConnect.Size = new System.Drawing.Size(75, 23);
            this.btnConnect.TabIndex = 25;
            this.btnConnect.Text = "Connect";
            this.btnConnect.UseVisualStyleBackColor = true;
            this.btnConnect.Click += new System.EventHandler(this.btnConnect_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(103, 10);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(85, 13);
            this.label1.TabIndex = 24;
            this.label1.Text = "Select COM port";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // cmbComPortSelect
            // 
            this.cmbComPortSelect.FormattingEnabled = true;
            this.cmbComPortSelect.Location = new System.Drawing.Point(87, 24);
            this.cmbComPortSelect.Name = "cmbComPortSelect";
            this.cmbComPortSelect.Size = new System.Drawing.Size(121, 21);
            this.cmbComPortSelect.TabIndex = 23;
            this.cmbComPortSelect.DropDown += new System.EventHandler(this.cmbComPortSelect_DropDown);
            this.cmbComPortSelect.SelectedIndexChanged += new System.EventHandler(this.cmbComPortSelect_SelectedIndexChanged);
            // 
            // serialPort1
            // 
            this.serialPort1.BaudRate = 2400;
            this.serialPort1.ReadTimeout = 1000;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.cmbComPortSelect);
            this.groupBox1.Controls.Add(this.btnStop);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.btnStart);
            this.groupBox1.Controls.Add(this.btnConnect);
            this.groupBox1.Controls.Add(this.btnDisconnect);
            this.groupBox1.Location = new System.Drawing.Point(3, 244);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(305, 84);
            this.groupBox1.TabIndex = 32;
            this.groupBox1.TabStop = false;
            this.groupBox1.Enter += new System.EventHandler(this.groupBox1_Enter);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.patientLastNameTextbox);
            this.groupBox2.Controls.Add(this.label4);
            this.groupBox2.Controls.Add(this.patientFirstNameTextbox);
            this.groupBox2.Controls.Add(this.label3);
            this.groupBox2.Controls.Add(this.patientLabel);
            this.groupBox2.Location = new System.Drawing.Point(3, 12);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(305, 68);
            this.groupBox2.TabIndex = 33;
            this.groupBox2.TabStop = false;
            // 
            // patientLastNameTextbox
            // 
            this.patientLastNameTextbox.Location = new System.Drawing.Point(220, 38);
            this.patientLastNameTextbox.Name = "patientLastNameTextbox";
            this.patientLastNameTextbox.Size = new System.Drawing.Size(81, 20);
            this.patientLastNameTextbox.TabIndex = 28;
            this.patientLastNameTextbox.TextChanged += new System.EventHandler(this.textBox2_TextChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(162, 38);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(53, 13);
            this.label4.TabIndex = 27;
            this.label4.Text = "Lastname";
            this.label4.Click += new System.EventHandler(this.label4_Click);
            // 
            // patientFirstNameTextbox
            // 
            this.patientFirstNameTextbox.Location = new System.Drawing.Point(65, 38);
            this.patientFirstNameTextbox.Name = "patientFirstNameTextbox";
            this.patientFirstNameTextbox.Size = new System.Drawing.Size(81, 20);
            this.patientFirstNameTextbox.TabIndex = 26;
            this.patientFirstNameTextbox.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(7, 38);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(52, 13);
            this.label3.TabIndex = 25;
            this.label3.Text = "Firstname";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // patientLabel
            // 
            this.patientLabel.AutoSize = true;
            this.patientLabel.Location = new System.Drawing.Point(118, 16);
            this.patientLabel.Name = "patientLabel";
            this.patientLabel.Size = new System.Drawing.Size(61, 13);
            this.patientLabel.TabIndex = 24;
            this.patientLabel.Text = "Patient Info";
            this.patientLabel.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.patientLabel.Click += new System.EventHandler(this.label2_Click_1);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.userTypeTextbox);
            this.groupBox3.Controls.Add(this.label8);
            this.groupBox3.Controls.Add(this.userLastNameTextbox);
            this.groupBox3.Controls.Add(this.label5);
            this.groupBox3.Controls.Add(this.userFirstNameTextbox);
            this.groupBox3.Controls.Add(this.label6);
            this.groupBox3.Controls.Add(this.label7);
            this.groupBox3.Location = new System.Drawing.Point(3, 86);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(305, 97);
            this.groupBox3.TabIndex = 34;
            this.groupBox3.TabStop = false;
            // 
            // userTypeTextbox
            // 
            this.userTypeTextbox.Location = new System.Drawing.Point(65, 72);
            this.userTypeTextbox.Name = "userTypeTextbox";
            this.userTypeTextbox.Size = new System.Drawing.Size(81, 20);
            this.userTypeTextbox.TabIndex = 30;
            this.userTypeTextbox.TextChanged += new System.EventHandler(this.textBox5_TextChanged);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(9, 72);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(31, 13);
            this.label8.TabIndex = 29;
            this.label8.Text = "Type";
            // 
            // userLastNameTextbox
            // 
            this.userLastNameTextbox.Location = new System.Drawing.Point(220, 38);
            this.userLastNameTextbox.Name = "userLastNameTextbox";
            this.userLastNameTextbox.Size = new System.Drawing.Size(81, 20);
            this.userLastNameTextbox.TabIndex = 28;
            this.userLastNameTextbox.TextChanged += new System.EventHandler(this.textBox3_TextChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(162, 38);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(53, 13);
            this.label5.TabIndex = 27;
            this.label5.Text = "Lastname";
            // 
            // userFirstNameTextbox
            // 
            this.userFirstNameTextbox.Location = new System.Drawing.Point(65, 38);
            this.userFirstNameTextbox.Name = "userFirstNameTextbox";
            this.userFirstNameTextbox.Size = new System.Drawing.Size(81, 20);
            this.userFirstNameTextbox.TabIndex = 26;
            this.userFirstNameTextbox.TextChanged += new System.EventHandler(this.textBox4_TextChanged);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(7, 38);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(52, 13);
            this.label6.TabIndex = 25;
            this.label6.Text = "Firstname";
            this.label6.Click += new System.EventHandler(this.label6_Click);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(118, 16);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(50, 13);
            this.label7.TabIndex = 24;
            this.label7.Text = "User Info";
            this.label7.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.label7.Click += new System.EventHandler(this.label7_Click);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(9, 16);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(85, 13);
            this.label9.TabIndex = 35;
            this.label9.Text = "Device Location";
            // 
            // deviceLocationTextbox
            // 
            this.deviceLocationTextbox.Location = new System.Drawing.Point(100, 16);
            this.deviceLocationTextbox.Name = "deviceLocationTextbox";
            this.deviceLocationTextbox.Size = new System.Drawing.Size(81, 20);
            this.deviceLocationTextbox.TabIndex = 36;
            this.deviceLocationTextbox.TextChanged += new System.EventHandler(this.textBox6_TextChanged);
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.label9);
            this.groupBox4.Controls.Add(this.deviceLocationTextbox);
            this.groupBox4.Location = new System.Drawing.Point(3, 189);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(305, 49);
            this.groupBox4.TabIndex = 37;
            this.groupBox4.TabStop = false;
            // 
            // Control
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.ClientSize = new System.Drawing.Size(315, 335);
            this.ControlBox = false;
            this.Controls.Add(this.groupBox4);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "Control";
            this.Text = "Control";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Load += new System.EventHandler(this.Control_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.groupBox4.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnStop;
        private System.Windows.Forms.Button btnStart;
        private System.Windows.Forms.Button btnDisconnect;
        private System.Windows.Forms.Button btnConnect;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox cmbComPortSelect;
        private System.IO.Ports.SerialPort serialPort1;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Label patientLabel;
        private System.Windows.Forms.TextBox patientLastNameTextbox;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox patientFirstNameTextbox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.TextBox userTypeTextbox;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox userLastNameTextbox;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox userFirstNameTextbox;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox deviceLocationTextbox;
        private System.Windows.Forms.GroupBox groupBox4;
    }
}
