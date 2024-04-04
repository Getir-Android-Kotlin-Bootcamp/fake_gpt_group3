<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
  <h1>Project Documentation</h1>
    <h1>A chat application built using Gemini and Kotlin XML.</h1>

<h1>Message Class</h1>
<p>This class represents the content and sending status of a message.</p>
<h2>Properties:</h2>
<ul>
  <li><strong>body:</strong> The content of the message.</li>
  <li><strong>isSent:</strong> A flag indicating whether the message is sent or not.</li>
</ul>

<h1>ChatAdapter Class</h1>
<p>This class is used to display chat messages within a RecyclerView.</p>
<h2>Properties:</h2>
<ul>
  <li><strong>messages:</strong> The list of messages.</li>
  <li><strong>isLoading:</strong> A flag to hold the loading status.</li>
</ul>
<h2>Methods:</h2>
<ul>
  <li><strong>getItemViewType(position: Int):</strong> Returns the type of the item at a specified position.</li>
  <li><strong>onCreateViewHolder(parent: ViewGroup, viewType: Int):</strong> Creates a ViewHolder for a specific type of item.</li>
  <li><strong>onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int):</strong> Binds the data at a specified position to the ViewHolder.</li>
  <li><strong>getItemCount():</strong> Returns the count of items in the list.</li>
  <li><strong>setLoading(loading: Boolean):</strong> Sets the loading status and notifies accordingly.</li>
</ul>

<h2>ViewHolder Classes:</h2>
<ul>
  <li><strong>SentMessageViewHolder:</strong> Represents sent messages.</li>
  <li><strong>ReceivedMessageViewHolder:</strong> Represents received messages.</li>
  <li><strong>LoadingViewHolder:</strong> Represents the loading state.</li>
</ul>

<h1>ChatFragment Class</h1>
<p>This class is a Fragment responsible for creating the chat interface.</p>
<h2>Properties:</h2>
<ul>
  <li><strong>isLoading:</strong> A flag to hold the loading status.</li>
</ul>
<h2>Methods:</h2>
<ul>
  <li><strong>checkConditions(questions: String):</strong> Checks if the user's query is valid.</li>
  <li><strong>onViewCreated(view: View, savedInstanceState: Bundle?):</strong> Called when the Fragment is created, initializes the UI components.</li>
  <li><strong>addMessageToDataSet(messageList: MutableList&lt;Message&gt;, message: Message, chatAdapter: ChatAdapter):</strong> Adds a new message to the message list and updates the adapter.</li>
  <li><strong>updateAdapter(chatAdapter: ChatAdapter):</strong> Called to update the adapter.</li>
</ul>

<h1>Other Files</h1>
<p><strong>safetySettingList:</strong> A list containing safety settings.</p>

</body>
</html>

<h1>Demo Video</h1>

https://github.com/erenkaraboga/fake_gpt/assets/74095539/27cb58b5-a858-41f2-9f96-9cc85097a4c2

