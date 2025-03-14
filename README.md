<h2>Firebase</h2>

<h4>Add Firebase Dependency</h4>

<h4>Project Level Build Gradle File</h4>
<pre>
id("com.google.gms.google-services") version "4.4.2" apply false
</pre>

<h4>Root Level Build Gradle File</h4>
<pre>
implementation(platform("com.google.firebase:firebase-bom:33.10.0"))
implementation("com.google.firebase:firebase-analytics")
</pre>

<h4>Real-Time Database Dependency</h4>
<pre>
implementation("com.google.firebase:firebase-database")
</pre>

<h4>Create Firebase Database Reference</h4>
<pre>
private lateinit var database: DatabaseReference

database = Firebase.database.reference
</pre>

<h4>Write Data into Firebase</h4>
<pre>
database.child("Users").push().setValue(user)
</pre>

<h4>Read Data from Firebase Real-Time Database</h4>

<h5>Add ValueEventListener to Read Data from Firebase</h5>
<pre>
val userDetailsRes = object : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        // Handle data here
    }
    
    override fun onCancelled(error: DatabaseError) {
        Log.e("FirebaseError", "Error: ${error.message}")
    }
}

database.child("Users").addValueEventListener(userDetailsRes)
</pre>
