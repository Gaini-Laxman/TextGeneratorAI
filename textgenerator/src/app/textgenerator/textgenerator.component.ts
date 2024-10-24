import { Component } from '@angular/core';

@Component({
  selector: 'app-text-generator',
  templateUrl: './textgenerator.component.html',
  styleUrls: ['./textgenerator.component.css']
})
export class TextGeneratorComponent {
  inputText: string = '';
  outputText: string = '';
  errorMessage: string = '';

  async generateText() {
    const apiUrl = 'http://localhost:8085/api/text/generate';

    // Clear previous messages
    this.outputText = '';
    this.errorMessage = '';

    try {
      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ prompt: this.inputText })
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      this.outputText = data.generatedText; // Adjust based on your API response structure
    } catch (error: any) {
      this.errorMessage = 'Error: ' + error.message;
    }
  }
}
