package com.example.quizmock1.data.model.firebase

class Scores{
   var correct: String = ""
   var wrong: String = ""
   fun component1(numCr: String){
      this.correct = numCr
   }
   fun component2(numWr: String){
      this.wrong = numWr
   }
}