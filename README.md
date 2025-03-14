<h2> Firebase </h2>

<h4> Add Firebase Dependency </h4>

<h4> Project Levet Build gradle File </h4>

<p> id("com.google.gms.google-services") version "4.4.2" apply false </p>

<h4> Root Levet Build gradle File </h4>

<p>implementation(platform("com.google.firebase:firebase-bom:33.10.0"))</p>
<p>implementation("com.google.firebase:firebase-analytics")</p>

<h4> Real Time database dependency </h4>
<p>implementation("com.google.firebase:firebase-database")</p>

<h4> Create firebase database reference </h4>
<p> private lateinit var database: DatabaseReference </p>
<p> database = Firebase.database.reference </p>

<h4> Write data into firebase </h4>
<p> database.child("Users").push().setValue(user) </p>
 
<h4>Read data from firebase real-time database </h4>
<h5> Add value event listener for read data from firebase </h5>
<p>  
  
  val userDetailsRes = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
            
  }
   override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error: ${error.message}")
            }         
</p>

<p> database.child("Users").addValueEventListener(userDetailsRes)</p>










