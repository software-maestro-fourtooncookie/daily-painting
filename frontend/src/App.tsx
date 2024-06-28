import React from 'react';
import logo from './logo.svg';
import './App.css';
import DiaryFormPage from './pages/DiaryFormPage/DiaryFormPage';
import { Route, Routes } from 'react-router-dom';
import DiaryResultPage from './pages/DiaryResultPage/DiaryResultPage';
import FeedbackPage from './pages/FeedbackPage/FeedbackPage';
import FeedbackThanksPage from "./pages/FeedbackThanksPage/FeedbackThanksPage";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<DiaryFormPage />} />
        <Route path="/diary/show" element={<DiaryResultPage />}/>
        <Route path="/feedback" element={<FeedbackPage />} />
        <Route path="/feedback-thanks" element={<FeedbackThanksPage />} />
      </Routes>
    </div>
  );
}

export default App;
