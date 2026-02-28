# Off‑Road Semantic Segmentation with UNet

Semantic segmentation of 10 off‑road terrain classes using a tuned UNet on the Duality AI synthetic dataset.

---

## 1. Project Overview

This project tackles **off‑road semantic segmentation**: for each pixel in an RGB image, the model predicts one of 10 terrain/object classes:

- Trees  
- Lush Bushes  
- Dry Grass  
- Dry Bushes  
- Ground Clutter  
- Flowers  
- Logs  
- Rocks  
- Landscape  
- Sky  

We compare several models (DINOv2 baseline, UNet, DeepLabV3, SegNet), run **data‑focused** and **training‑focused** experiments, and finally deploy a **UNet + Brightness/Contrast augmentation +  CE + Dice loss** model as our best configuration.

Key validation result (fill from your final eval):

- **Mean IoU:**   
- **Mean Dice:** ``  
- **Pixel accuracy:** `TODO_PIXEL_ACC`  

---

## 2. Repository Structure

```text
.
├── data/
│   ├── Train/
│   │   ├── Color_Images/
│   │   └── Segmentation/
│   ├── Val/
│   │   ├── Color_Images/
│   │   └── Segmentation/
│   └── Test/
│       └── Color_Images/
├── ENV_SETUP/                 # (optional) environment scripts / instructions
├── models/
│   ├── final_unet/
│   │   ├── best_model_final.pth
│   │   └── final_model.pth
│   └── Models.md              # detailed model experiments
├── results/
│   └── final_model/
│       ├── metrics.json
│       ├── class_iou.json
│       ├── best_cases/
│       └── failures/
├── scripts/
│   ├── dataset.py
│   ├── train_final_unet.py
│   ├── eval_final_unet.py     # evaluation script (see Section 8)
│   └── utils_metrics.py       # (optional) metric helpers
├── notebooks/
│   ├── ModelExp1_UNet.ipynb
│   ├── Exp_002_Brightness_Contrast.ipynb
│   ├── T1_-Learning-Rate.ipynb
│   ├── T3_-Loss-functions.ipynb
│   ├── T5_-Batch-size.ipynb
│   └── Final_UNet_Training.ipynb
├── Experiment_Tracker.xlsx
├── Report.md                  # full technical report
├── README.md
└── requirements.txt
```