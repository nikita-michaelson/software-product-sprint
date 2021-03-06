// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random quote or fact to the page.
 */
function addRandomQuoteOrFact() {
  const quotesOrFacts =
      ['Loves spicy food', 'Can speak some Korean', '"My Cabbages!"', '"My own mother thought that I was a monster.She was right of course but it still hurt!"'];

  // Pick a random greeting.
  const greeting = quotesOrFacts[Math.floor(Math.random() * quotesOrFacts.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function hello(){
   fetch('/data').then(response=>response.json()).then((hi)=>{
    document.getElementById('response').innerText = hi;
  });
}


/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}