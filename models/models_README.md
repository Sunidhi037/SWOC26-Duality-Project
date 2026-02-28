# Trained Models

Due to GitHub's 100MB file size limit, all trained model weights are hosted on Google Drive.

## 📥 Download Model Weights

### Phase 1-2: Baseline Model

**File:** `best_model.pth`  
**Download:** [Google Drive Link](https://drive.google.com/drive/folders/15k6pszXAXEqCrB9rTtACw4kNu6Q1AclO?usp=sharing)

**Model Details:**

- **Architecture:** DINOv2-small (frozen backbone) + ConvNeXt decoder
- **Input Size:** 266 x 476 pixels
- **Output Classes:** 10 semantic classes
- **Trainable Parameters:** ~528K (decoder only)
- **Model Size:** ~150 MB

**Performance Metrics:**

| Metric                | Value                |
| --------------------- | --------------------:|
| Validation IoU        | **0.2574**           |
| Validation Dice Score | 0.3937               |
| Pixel Accuracy        | 0.6797               |
| Inference Speed       | **13.10 ms/image** ✅ |

✅ **Speed Requirement Met:** <50ms per image

---

## 🚀 Usage

### 1. Download the Model

Visit the Google Drive link above and download `best_model.pth` to your local machine.

### 2. Run Inference

```bash
# Test on validation data
python scripts/test_segmentation.py \
    --model_path path/to/best_model.pth \
    --data_dir path/to/validation/data \
    --output_dir results/predictions

# Test on unseen test data (for submission)
python scripts/test_segmentation.py \
    --model_path path/to/best_model.pth \
    --data_dir path/to/test/data \
    --output_dir results/test_predictions \
    --test_mode
```

---

## 🏗️ Model Architecture

```
Input Image (266x476x3)
        ↓
DINOv2-small Backbone (frozen)
├── 12 Transformer blocks
├── Patch size: 14x14
└── Output: 384-dim embeddings
        ↓
ConvNeXt-style Decoder (trainable)
├── Stem: Conv2d (384 → 128 channels)
├── Depthwise Conv Block (128 channels)
└── Classifier: Conv2d (128 → 10 classes)
        ↓
Output Segmentation Map (266x476x10)
```

---

## 📊 Training Configuration

- **Dataset:** Offroad Segmentation Training Dataset
  - Train: 2857 images
  - Validation: 317 images
- **Optimizer:** SGD (momentum=0.9)
- **Learning Rate:** 0.0001
- **Batch Size:** 8
- **Epochs:** 50
- **Loss Function:** CrossEntropyLoss
- **Training Time:** ~3 hours (Google Colab T4 GPU)

---

## 🎯 Segmentation Classes

The model predicts 10 semantic classes:

| Class ID | Class Name     | Color (Visualization) |
| -------- | -------------- | --------------------- |
| 0        | Background     | Black                 |
| 1        | Trees          | Forest Green          |
| 2        | Lush Bushes    | Lime                  |
| 3        | Dry Grass      | Tan                   |
| 4        | Dry Bushes     | Brown                 |
| 5        | Ground Clutter | Olive                 |
| 6        | Logs           | Saddle Brown          |
| 7        | Rocks          | Gray                  |
| 8        | Landscape      | Sienna                |
| 9        | Sky            | Sky Blue              |

---

## 📈 Known Limitations (Phase 1-2 Baseline)

Based on validation results, this baseline model has:

1. **Class Imbalance Impact:**
   
   - Strong performance on dominant classes (Sky, Landscape, Dry Grass)
   - Weaker performance on rare classes (Logs, Flowers, Dry Bushes)

2. **Small Object Detection:**
   
   - Struggles with small, scattered objects (Logs, small Rocks)
   - Boundary precision needs improvement

3. **Similar Class Confusion:**
   
   - Confuses Dry Grass ↔ Dry Bushes
   - Confuses Ground Clutter ↔ Rocks

**Phase 3+ improvements will address these issues through:**

- Class-weighted loss functions
- Data augmentation
- Focal loss for small objects

---

## 🔄 Future Models

As experiments progress, improved models will be added here:

- **Phase 3 - Augmentation Model:** (Coming soon)
- **Phase 3 - Weighted Loss Model:** (Coming soon)
- **Phase 4 - Final Optimized Model:** (Coming soon)

---

## 📝 Model Checkpoints

Additional checkpoints from training are available in the same Google Drive folder:

- `checkpoint_epoch_10.pth` - Early checkpoint (10 epochs)
- `checkpoint_epoch_20.pth` - Mid checkpoint (20 epochs)
- `checkpoint_epoch_30.pth` - Late checkpoint (30 epochs)
- `checkpoint_epoch_40.pth` - Late checkpoint (40 epochs)
- `best_model.pth` - Best validation IoU model ✅ **(Use this)**
- `segmentation_head_final.pth` - Final epoch model

**Recommendation:** Use `best_model.pth` for inference (best validation performance).

---

## 🛠️ Model Loading Example

```python
import torch
from scripts.train_segmentation import SegmentationHeadConvNeXt

# Load model architecture
device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
model = SegmentationHeadConvNeXt(
    in_channels=384,  # DINOv2-small embedding dim
    out_channels=10,  # Number of classes
    tokenW=34,        # 476 // 14
    tokenH=19         # 266 // 14
)

# Load trained weights
model.load_state_dict(torch.load('best_model.pth', map_location=device))
model = model.to(device)
model.eval()

# Ready for inference!
```

---

## 📞 Support

For issues or questions about the model:

- See training logs in `results/baseline/evaluation_metrics.txt`
- Check training curves in `results/baseline/`
- Review code in `scripts/train_segmentation.py`

---

*Last Updated: February 7, 2026*  
*Model Version: Phase 1-2 Baseline*
