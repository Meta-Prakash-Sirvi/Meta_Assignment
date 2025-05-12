import React, { useState, useEffect } from "react";



export default function TaskModal({ isOpen, onClose, onSave, taskData }) {
const [title, setTitle] = useState("");
const [description, setDescription] = useState("");
const [priority, setPriority] = useState("Medium");

useEffect(() => {
if (taskData) {
setTitle(taskData.title || "");
setDescription(taskData.description || "");
setPriority(taskData.priority || "Medium");
} else {

setTitle("");
setDescription("");
setPriority("Medium");
}
}, [taskData, isOpen]); 

if (!isOpen) {
return null;
}

const handleSubmit = (e) => {
e.preventDefault();
if (!title.trim()) {
alert("Title is required.");
return;
}
onSave({
...(taskData || {}), 
title,
description,
priority,
});
};

return (
<div className="modal-overlay" onClick={onClose}>
<div className="modal-content" onClick={(e) => e.stopPropagation()}>
<h3>{taskData ? "Edit Task" : "Create New Task"}</h3>
<form onSubmit={handleSubmit}>
<div>
<label htmlFor="title">Title:</label>
<input
type="text"
id="title"
value={title}
onChange={(e) => setTitle(e.target.value)}
required
/>
</div>
<div>
<label htmlFor="description">Description:</label>
<textarea
id="description"
value={description}
onChange={(e) => setDescription(e.target.value)}
/>
</div>
<div>
<label htmlFor="priority">Priority:</label>
<select
id="priority"
value={priority}
onChange={(e) => setPriority(e.target.value)}
>
<option value="Low">Low</option>
<option value="Medium">Medium</option>
<option value="High">High</option>
</select>
</div>
<div className="modal-actions">
<button type="submit">Save Task</button>
<button type="button" onClick={onClose}>
Cancel
</button>
</div>
</form>
</div>
</div>
);
}
